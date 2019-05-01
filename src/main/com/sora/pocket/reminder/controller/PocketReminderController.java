package com.sora.pocket.reminder.controller;

import com.sora.pocket.reminder.entity.Item;
import com.sora.pocket.reminder.entity.Retrieve;
import com.sora.pocket.reminder.form.RetrieveForm;
import com.sora.pocket.reminder.service.PocketReminderService;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PocketReminderController {

  private final PocketReminderService pocketReminderService;

  @GetMapping("/index")
  public String index(Model model, @RequestParam String token) {
    model.addAttribute("retrieveForm", new RetrieveForm(token));
    return "index";
  }
  
  @GetMapping("/get")
  public String getList(Model model,@Validated @ModelAttribute RetrieveForm form) {
    String accessToken = pocketReminderService.auth(form.getToken());
    log.info("accessToken: {}", accessToken);
    Retrieve retrieve = pocketReminderService.fetch(accessToken);
    List<Item> items = retrieve.getList().entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
    model.addAttribute("items", items);
    return "list";
  }
}
