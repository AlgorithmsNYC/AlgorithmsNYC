import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem_005_apliszka {

  int points(String[] player, String[] dictionary) {
    Set<String> playerSet = new HashSet<String>(Arrays.asList(player));
    Set<String> dictionarySet = new HashSet<String>(Arrays.asList(dictionary));
    int points = 0;

    for (String word : playerSet)
      if (dictionarySet.contains(word)) points += word.length() * word.length();

    return points;
  }

  @Test
  public void test1() {
    Assert.assertEquals(points(new String[] {"apple", "orange", "strawberry"}, new String[] {"strawberry", "orange", "grapefruit", "watermelon"}), 136);
  }

  @Test
  public void test2() {
    Assert.assertEquals(points(new String[] {"apple"}, new String[] {"strawberry", "orange", "grapefruit", "watermelon"}
    ), 0);
  }

  @Test
  public void test3() {
    Assert.assertEquals(points(new String[] {"orange", "orange"}, new String[] {"strawberry", "orange", "grapefruit", "watermelon"}), 36);
  }

  @Test
  public void test4() {
    Assert.assertEquals(points(new String[] {"lidi", "o", "lidi", "gnbewjzb", "kten", "ebnelff", "gptsvqx", "rkauxq", "rkauxq", "kfkcdn"}
      , new String[] {"nava", "wk", "kfkcdn", "lidi", "gptsvqx", "ebnelff", "hgsppdezet", "ulf", "rkauxq", "wcicx"}), 186);
  }
}

