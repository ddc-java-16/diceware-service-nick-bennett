package edu.cnm.deepdive.dicewareservice.controller;

import edu.cnm.deepdive.dicewareservice.service.PassphraseProvider;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PassphraseController {

  private final PassphraseProvider provider;

  @Autowired
  public PassphraseController(PassphraseProvider provider) {
    this.provider = provider;
  }

  @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> get(@RequestParam(defaultValue = "5") int length) {
    return provider.generate(length);
  }

  @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String get(
      @RequestParam(defaultValue = "5") int length,
      @RequestParam(defaultValue = " ") String delimiter
  ) {
    return String.join(delimiter, get(length));
  }

  @GetMapping(value = "/random", produces = MediaType.TEXT_HTML_VALUE)
  public String get(@RequestParam(defaultValue = "5") int length, Model model) {
    model.addAttribute("words", get(length));
    return "random";
  }

}
