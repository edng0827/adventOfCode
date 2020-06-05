import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void botInstructions() {
        Map<Integer, Day10.Bot> testBot = new HashMap<>();
        testBot.put(1, new Day10.Bot());
        //test with existing bot
        Day10.botInstructions(testBot, "test", 1);
        assertEquals("test", testBot.get(1).getInstructions());

        //test with new bot
        Day10.botInstructions(testBot, "test2", 2);
        assertEquals("test2", testBot.get(2).getInstructions());
    }

    @Test
    void botChips() {
        Map<Integer, Day10.Bot> testBot = new HashMap<>();
        testBot.put(1, new Day10.Bot());
        //test with existing bot first chip default to low
        Day10.botChips(testBot, 2, 1);
        assertEquals(2, testBot.get(1).getLowChip());

        //add lower value chip, swap high with low
        Day10.botChips(testBot, 1, 1);
        assertEquals(1, testBot.get(1).getLowChip());
        assertEquals(2, testBot.get(1).getHighChip());

        //repeat with new bot
        Day10.botChips(testBot, 2, 2);
        assertEquals(2, testBot.get(2).getLowChip());

        //add lower value chip, swap high with low
        Day10.botChips(testBot, 3, 2);
        assertEquals(2, testBot.get(2).getLowChip());
        assertEquals(3, testBot.get(2).getHighChip());
    }

    @Test
    void runBots() {
        //bot puts output in with proper keys according to instructions
        Map<Integer, Day10.Bot> robots = new HashMap<>();
        Map<Integer, Integer> outputs = new HashMap<>();
        Day10.Bot robut = new Day10.Bot();
        robut.setLowChip(1);
        robut.setHighChip(2);
        robut.setInstructions("bot 1 gives low to output 530 and high to output 720");
        robots.put(1, robut);
        Day10.runBots(robots, outputs);
        assertEquals(new Integer(1), outputs.get(530));
        assertEquals(new Integer(2), outputs.get(720));
    }

    @Test
    void findRunningBot() {
        Map<Integer, Day10.Bot> robuts = new HashMap<>();
        //does not return bot until high and low chip are both set
        robuts.put(1, new Day10.Bot());
        assertNull(Day10.findRunningBot(robuts));
        //set only 1
        robuts.get(1).setChip(5);
        assertNull(Day10.findRunningBot(robuts));
        //set both
        robuts.get(1).setChip(10);
        assertEquals(robuts.get(1), Day10.findRunningBot(robuts));
    }

    @Test
    void runInstructions() {
        Map<Integer, Day10.Bot> robots = new HashMap<>();
        Map<Integer, Integer> outputs = new HashMap<>();

        //give chips to output
        Day10.Bot robut = new Day10.Bot();
        robut.setLowChip(1);
        robut.setHighChip(2);
        robut.setInstructions("bot 1 gives low to output 530 and high to output 720");
        robots.put(1, robut);
        Day10.runInstructions(robots, outputs, robut);
        assertEquals(new Integer(1), outputs.get(530));
        assertEquals(new Integer(2), outputs.get(720));

        //give chips to other bots
        Day10.Bot produce = new Day10.Bot();
        produce.setLowChip(1);
        produce.setHighChip(2);
        produce.setInstructions("bot 2 gives low to bot 4 and high to bot 5");
        robots.put(2, produce);
        robots.put(4, new Day10.Bot());
        robots.put(5, new Day10.Bot());
        Day10.runInstructions(robots, outputs, produce);
        assertEquals(1, robots.get(4).getLowChip());
        assertEquals(2, robots.get(5).getLowChip());
    }

    @Test
    void chipBotExchange() {
        Map<Integer, Day10.Bot> robots = new HashMap<>();
        Map<Integer, Integer> outputs = new HashMap<>();

        //test get
        Day10.Bot testGet = new Day10.Bot();
        robots.put(1, testGet);
        Day10.chipBotExchange(1, 2, robots, outputs);
        assertEquals(2,testGet.getLowChip());

        //test run instructions
        testGet.setInstructions("bot 1 gives low to bot 2 and high to output 1");
        Day10.Bot consume = new Day10.Bot();
        robots.put(2, consume);
        Day10.chipBotExchange(1, 4, robots, outputs);
        assertEquals(2,consume.getLowChip());
        assertEquals(new Integer (4), outputs.get(1));

        //test find desired bot
        Day10.Bot desired = new Day10.Bot();
        desired.setHighChip(Day10.HIGH);
        robots.put(3, desired);
        Day10.chipBotExchange(3, Day10.LOW, robots, outputs);
        assertEquals(3, Day10.BOT_ID);
    }
}