package com.sora.pocket.reminder.form;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RetrieveForm {
  @NotNull private String token;
}
