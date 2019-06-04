package com.sora.pocket.reminder.controller;

import com.sora.pocket.reminder.config.ApiConfig;
import com.sora.pocket.reminder.entity.Item;
import com.sora.pocket.reminder.entity.Retrieve;
import com.sora.pocket.reminder.form.RetrieveForm;
import com.sora.pocket.reminder.service.AuthRepository;
import com.sora.pocket.reminder.service.PocketReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PocketReminderController {

    private final PocketReminderService pocketReminderService;
    private final AuthRepository authRepository;
    private final ApiConfig apiConfig;

//    @Value("${pocket.consumer-key}")
//    private String key:

    @GetMapping("/index")
    public String index(Model model, @RequestParam String token) {
        model.addAttribute("retrieveForm", new RetrieveForm(token));
        return "index";
    }

    @GetMapping("/get")
    public String getList(Model model, @Validated @ModelAttribute RetrieveForm form) {
        String accessToken = getAccessToken(apiConfig.getConsumerKey(), form.getToken());
        log.info("accessToken: {}", accessToken);
        Retrieve retrieve = pocketReminderService.fetch(accessToken);
        List<Item> items = retrieve.getList().entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
        model.addAttribute("items", items);
        return "list";
    }

    private String getAccessToken(String key, String token) {
        String accessToken = authRepository.fineOne(key);
        if (accessToken == null) {
            accessToken = pocketReminderService.auth(token);
            authRepository.insert(key, accessToken);
        }
        return accessToken;
    }
}
