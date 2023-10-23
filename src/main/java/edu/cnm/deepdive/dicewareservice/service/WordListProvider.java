package edu.cnm.deepdive.dicewareservice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class WordListProvider implements WordProvider {

  private final List<String> words;

  @Autowired
  public WordListProvider(@Value("${diceware.word-list-name}") String wordListFile) {
    Resource resource = new ClassPathResource(wordListFile);
    try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
      words = stream
          .map(String::strip)
          .filter((line) -> !line.isEmpty())
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<String> words() {
    return words;
  }

}
