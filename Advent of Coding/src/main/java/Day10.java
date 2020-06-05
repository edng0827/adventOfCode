import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day10 {
    public static String INPUT = "src/main/files/day10";
    public static int HIGH = 61;
    public static int LOW = 17;
    public static int BOT_ID;

    public static void main(String[] args) throws IOException {
        Map<Integer, Bot> robots = new HashMap<>();
        Map<Integer, Integer> outputBox = new HashMap<>();

        BufferedReader buff = new BufferedReader(new FileReader(INPUT));
        String instruction;
        while((instruction = buff.readLine())!= null) {
            String[] parse = instruction.split(" ");
            if(instruction.contains("gives")){
                botInstructions(robots, instruction, Integer.valueOf(parse[1]));
            } else if(instruction.contains("goes")){
                botChips(robots, Integer.valueOf(parse[1]), Integer.valueOf(parse[5]));
            }
        }

        runBots(robots, outputBox);
        System.out.println("found bot with id: " + BOT_ID);
        System.out.println("Multiplying outputs: " + outputBox.get(0) * outputBox.get(1) * outputBox.get(2));
    }

    public static void botInstructions(Map<Integer, Bot> robots, String instruction, int botId){
        Bot robot = robots.get(botId);
        if(robot != null){
            robot.setInstructions(instruction);
        } else {
            robot = new Bot();
            robot.setInstructions(instruction);
            robots.put(botId, robot);
        }
    }

    public static void botChips(Map<Integer, Bot> robots, int chip, int botId){
        Bot robot = robots.get(botId);
        if(robot != null){
            robot.setChip(chip);
        } else {
            robot = new Bot();
            robot.setChip(chip);
            robots.put(botId, robot);
        }
    }

    public static void runBots(Map<Integer, Bot> robots, Map<Integer, Integer> out){
        while(findRunningBot(robots) != null){
            Bot running = findRunningBot(robots);
            if(running.getInstructions() != null){
                runInstructions(robots, out, running);
            }
        }
    }

    public static Bot findRunningBot(Map<Integer, Bot> robots){
        for(Map.Entry<Integer, Bot> robot: robots.entrySet()){
            if(robot.getValue().getLowChip() > 0 && robot.getValue().getHighChip() > 0){
                int botId = robot.getKey();
                return robots.get(botId);
            }
        }
        return null;
    }

    public static void runInstructions(Map<Integer, Bot> robots, Map<Integer, Integer> out, Bot running){
        String[] instructions = running.getInstructions().split(" ");
        int lowTarget = Integer.valueOf(instructions[6]);
        int highTarget = Integer.valueOf(instructions[11]);
        //give low chip
        if(instructions[5].equals("bot")){
            chipBotExchange(lowTarget, running.getLowChip(), robots, out);
        } else if(instructions[5].equals("output")){
            out.put(lowTarget, running.getLowChip());
        }
        running.setLowChip(0);

        //give high chip
        if(instructions[10].equals("bot")){
            chipBotExchange(highTarget, running.getHighChip(), robots, out);
        } else if(instructions[10].equals("output")){
            out.put(highTarget, running.getHighChip());
        }
        running.setHighChip(0);
    }

    public static void chipBotExchange(int botId, int chip, Map<Integer, Bot> robots, Map<Integer, Integer> out){
        Bot receiver = robots.get(botId);
        if(receiver != null){
            receiver.setChip(chip);
            if(receiver.getLowChip() == LOW && receiver.getHighChip() == HIGH){
                BOT_ID = botId;
            }

            if(receiver.getInstructions() != null && receiver.getLowChip() != 0 && receiver.getHighChip() != 0){
                runInstructions(robots, out, receiver);
            }
        }
    }

    //Bot class
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Bot {
        private int lowChip = 0;
        private int highChip = 0;
        private String instructions;

        public void setChip(int chip){
            if(this.lowChip == 0){
                this.lowChip = chip;
            } else if(chip > this.lowChip && this.highChip == 0){
                this.highChip = chip;
            } else if(chip > this.highChip && this.highChip != 0){
                this.lowChip = this.highChip;
                this.highChip = chip;
            } else {
                this.highChip = this.lowChip;
                this.lowChip = chip;
            }
        }
    }
}
