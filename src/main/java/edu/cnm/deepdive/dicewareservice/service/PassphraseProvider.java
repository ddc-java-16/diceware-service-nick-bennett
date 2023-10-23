package edu.cnm.deepdive.dicewareservice.service;

import java.util.List;

public interface PassphraseProvider {

  List<String> generate(int length);

}
