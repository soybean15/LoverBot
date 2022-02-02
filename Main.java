
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



class Bot {
     HashMap<Integer, String[]> botMessages = new HashMap<>();
     List<String> unknown = new ArrayList<>();

     BotResponse br = new BotResponse(this);


    public Bot(){
        br.generateMessages();

    }



     int replyPercentage(String[] msg, String[] messageRequirement, String[] important){

        int percentage=0;
        for(String str: msg){
            if(Arrays.asList(important).contains(str)){
                percentage+=10;
                if(Arrays.asList(messageRequirement).contains(str)){
                    percentage+=2;
                }
            }
        }

        return percentage;
    }



    int getMax(List<Integer> arr){
        int max = arr.get(0);
        for (int n : arr){
            if (n>max) max =n;
        }
        return max;
    }

    int getRandomNumber(int max) {
        return new Random().nextInt(max+1);
    }

    String generate_message(String[] msg){

        List<Integer> rp =br.messageRequirements(msg);
        int max = getMax(rp);

        String respond ="";

        if(max == 0){
            respond = unknown.get(getRandomNumber(unknown.size()-1));
        }else {
            int index=rp.indexOf(max);

            int size = botMessages.get(index).length-1;

            respond = botMessages.get(index)[getRandomNumber(size)];

        }


        return respond;
    }

    String response(String message){
        String[] msg =message.split("[\\s,.?:-]");

        return generate_message(msg);
    }


}


 class Bot_2 extends Bot{

    HashMap<Integer, String[]> chatSequence = new HashMap<>();
    int count = 0;
    static boolean stop = false;

    public Bot_2(){
        generateChat();
    }


    void generateChat(){
        chatSequence.put(0, new String[]{"Good Morning"});
        chatSequence.put(1, new String[]{"Kumain ka na?"});
        chatSequence.put(2, new String[]{"Buti namn,sarap siguro ng ulam mo","kain kna po libre kita"});
        chatSequence.put(3, new String[]{"May tanong pala ko haha"});
        chatSequence.put(4, new String[]{"Floor wax ka ba?"});
        chatSequence.put(5, new String[]{"I love you, ay sorry nadulas haha"});
        chatSequence.put(6, new String[]{"Ganda mo pala ngayon :)"});
        chatSequence.put(7, new String[]{"haha haba ng hair", "oo nga po haha","yieeh humble haha"});//(acceptance)(not sure)(denial)
        chatSequence.put(8, new String[]{"uhm..gusto ko lng sana sabihin na gusto kita"});
        chatSequence.put(9, new String[]{"sorry kung nabigla ka, nakakahiya tuloy "});
        chatSequence.put(10, new String[]{"Nga pala dba sabi mo wala ka lakad sa 14?"});
        chatSequence.put(11, new String[]{"Mall tayo? libre ko"});
        chatSequence.put(12, new String[]{"haha yown haha excited nko haha","ayy wag ka po mahiya ako lng namn to","oo basta ikaw"});//sige, kakahiya, sure ka?
        chatSequence.put(13, new String[]{"Sakto wla tau kavalentines kaya tayo nalng"});
        chatSequence.put(14, new String[]{"haha wala na atrasan ah ?"});
        chatSequence.put(15, new String[]{"wait lng po ah"});
        chatSequence.put(16, new String[]{"Sabhin ko kay mama ako na maghugas ng plato"});
        chatSequence.put(17, new String[]{"Maya ulet haha bye na<3"});
    }

    List<Integer> messageRequirements(String[] msg){
        List<Integer> reply_percentage = new ArrayList<>();

        if(count==2){
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"oo", "tapos","opo","kanina","kakatapos"}));
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"hindi", "di","gutom","wala"}));
        }else if (count==7){
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"salamat","thank", "thanks","oo","ikr","know"}));
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"ay", "ayy","haha","weh","di nga","echosero","bolero","joker"}));
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"hindi", "loko","di","najojoke","baliw", "wala","magpapalibre"}));

        }else if(count==12){
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"sige","okie", "okay","ok","tara","g","go"}));
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"ay", "ayy","haha","weh","di nga","kakahiya","hiya"}));
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"kahiya","wala","magpapalibre","nakain","libre"}));

        }
        else {
            reply_percentage.add(replyPercentage(msg,new String[]{""}, new String[]{"yieeh","sakto","lang","sige","bket","Remove","speed","bilis","hahaha","bye","baket","hi","hello","ano","thanks","salamat","sige","morning","morning","good","opo","oo","haha","ok","okay"}));
        }
        return reply_percentage;
    }


    String generate_message(String[] msg){
        List<Integer> rp =messageRequirements(msg);

        int max = getMax(rp);

        String respond ="";

        if(max == 0){
            respond = unknown.get(getRandomNumber(unknown.size()-1));
        }else {
            int index=rp.indexOf(max);
            respond = chatSequence.get(count)[index];
            count++;
        }


        return respond;
    }

    String response(String message){
        String[] msg =message.split("[\\s,.?:-]");

        if(count>=17){
            stop=true;
        }

        return generate_message(msg);
    }

}


class BotResponse {

    Bot bot;

    public BotResponse(Bot bot){
        this.bot = bot;
    }


    void generateMessages(){
        bot.botMessages.put(0, new String[]{"hello","hi"});//greet
        bot.botMessages.put(1, new String[]{"hindi pa","kakatapos lang","opo"});//kumain kna
        bot.botMessages.put(2, new String[]{"wala po","facebook lang","wala nga eh","nanonood"});//ano gawa mo
        bot.botMessages.put(3, new String[]{"eto tambay","okay lang naman","medyo busy hehe","eto buhay pa"});//kumusta
        bot.botMessages.put(4, new String[]{"hindi ah","malabo lang mata mo","bolero"});//ganda mo naman
        bot.botMessages.put(5, new String[]{"sige sige","Ok","kk"});

        //unrecognized message
        bot.unknown.add("di ko gets");
        bot.unknown.add("pakilinaw po");
        bot.unknown.add("ano yon?");
        bot.unknown.add("di ko maintindihan");
    }

     List<Integer> messageRequirements(String[] msg){
        List<Integer> reply_percentage = new ArrayList<>();


        reply_percentage.add(bot.replyPercentage(msg,new String[]{""}, new String[]{"hi", "hello"}));
        reply_percentage.add(bot.replyPercentage(msg,new String[]{"kumain","ka","na"}, new String[]{"kumain", "kain"}));
        reply_percentage.add(bot.replyPercentage(msg,new String[]{"ano","gawa","mo"}, new String[]{"gawa", "ginagawa"}));
        reply_percentage.add(bot.replyPercentage(msg,new String[]{"kumusta","ka", "na"}, new String[]{"kumusta", "musta", "balita"}));
        reply_percentage.add(bot.replyPercentage(msg,new String[]{"ang","cute","ganda","mo","naman"}, new String[]{"cute", "ganda"}));
         reply_percentage.add(bot.replyPercentage(msg,new String[]{"ok", "okay","k"}, new String[]{"ok", "okay","k"}));

        return reply_percentage;
    }



}




class Screen {
    private final List<String> messages = new ArrayList<>();
    private char[][] screen = new char[20][50];

    Bot bot = new Bot() ;
    Bot_2 b2 =new Bot_2();

    void addMessage(String str){

        messages.add(str);
        String s = b2.response(str);

        messages.add(s);

        clear();
        if(str.equals("Remove First Text")) removeFirstMessage();
        addTextOnScreen();
    }

    void botMessage(String str){

        messages.add(str);
        messages.add(bot.response(str));

        clear();
        addTextOnScreen();
    }
    void removeFirstMessage(){
        messages.remove(0);

    }
    void generateScreen() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 50; j++) {
                screen[i][j] = ' ';



                //outer border

                if (i == 0 || i == 19) {

                    screen[i][j] = '-';

                }
                if (j == 0 || j == 49) {

                    screen[i][j] = '|';

                }


                //inner border
                if(i==1 || i==14){
                    if(j>=1 && j<=48)
                    screen[i][j] = '-';
                }
                if (j == 1 || j == 48) {
                    if (i>=1 && i <= 14) {
                        screen[i][j] = '|';
                    }
                }

                //send button
                String send = "S E N D";
                if(i>14 && i<18 && j>30 && j<48){
                    if(i==16){
                        if( j >33 && j<41){
                            int index = j-34;
                            screen[i][j]=send.charAt(index);
                        }
                    }
                    if(i==15 || i ==17){
                        screen[i][j]='-';
                    }
                    if(j ==31 || j == 47){
                        screen[i][j] = '+';
                        if(i==16){
                            screen[i][j] = '|';
                        }

                    }
                }

                //messagebox
                String messagebox = "message box";

                if(i>14 && i<19 && j>1 && j<30){

                    if(i==15 || i==18){
                        screen[i][j]='-';
                    }
                    if(j==2 || j==29 ){
                        screen[i][j]='+';
                        if(i==16|| i==17){
                            screen[i][j]='|';
                        }
                    }
                    if(i==15){
                        if(j>3 && j<15){
                            int index = j-4;
                            screen[i][j]=messagebox.charAt(index);
                        }
                    }
                }
                screen[0][0]='+';
                screen[19][0]='+';
                screen[0][49]='+';
                screen[19][49]='+';

                screen[1][1]='+';
                screen[14][1]='+';
                screen[1][48]='+';
                screen[14][48]='+';

            }
        }
    }

    void  messageBox(String msg){
        int len = msg.length();

        for (int i = 4; i<len+4; i++ ){
            int index = i-4;
            screen[16][i] =msg.charAt(index);
        }
    }

    void printScreen(){
        for (int i = 0; i<20; i++){
            for (int j=0; j<50; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }
    }

    void newMessageScreen(){
        String newMessage = "1 New Message";

        int len = newMessage.length();
        int end = len+19;

            for (int i = 15; i<end; i++){

                screen[6][i]='-';
                screen[8][i]='-';

                if(i>16 && i<(end-2)){
                    int index = i-17;
                    screen[7][i]=newMessage.charAt(index);
                }
                if(i==15 || i ==end-1){
                    screen[6][i]='+';
                    screen[7][i]='|';
                    screen[8][i]='+';
                }

            }


        String enter = "Enter any key to read.";
        len = enter.length();
        end = len +3;
        for (int i = 3; i<end; i++){
            int index = i-3;
            screen[13][i]=enter.charAt(index);
        }
    }

    void clear(){
        for (int i =2; i<14 ;i++){

            for (int j =2; j<48 ;j++){
                screen[i][j]=' ';

            }
        }
    }

    void leftMessage(int idx , int index){
        int len = messages.get(idx).length();
        int end = len+4;


        for(int i=4, j=0; i<end+1; i++){

            screen[index][i] = '-';
             if(i<end)screen[index+1][i]= messages.get(idx).charAt(j++);
            screen[index+2][i] = '-';
        }
        screen[index][end]='+';
        screen[index+1][end]='|';
        screen[index+2][end]='+';

        screen[index][3]='+';
        screen[index+1][3]='|';
        screen[index+2][3]='+';

    }

    void rightMessage(int idx , int index){

        int len = messages.get(idx).length();
        int end = 45-len;

        for(int i=45, j=len-1; i>(end-1); i--){

            screen[index][i] = '-';
            if(i>end) screen[index+1][i]= messages.get(idx).charAt(j--);
            screen[index+2][i] = '-';
        }
        screen[index][end-1]='+';
        screen[index+1][end-1]='|';
        screen[index+2][end-1]='+';

        screen[index][46]='+';
        screen[index+1][46]='|';
        screen[index+2][46]='+';

    }
    void addTextOnScreen(){

        int count =0;
        int index=11;
        int num =0;
        for(int idx = messages.size()-1; idx>=0;idx--){

            if(num%2==1){
                rightMessage(idx,index);
            }else {
                leftMessage(idx, index);
            }
            if(count>=3) break;

            index-=3;
            num++;
            count++;
        }
    }

}


class LoveBot {
    Screen screen = new Screen();
    Scanner sc = new Scanner(System.in);

    String message(String prompt){
        System.out.print(prompt);
        return sc.nextLine();
    }
    void start(){
        screen.generateScreen();

        screen.newMessageScreen();
        screen.printScreen();
        message("Read Message:");
        screen.clear();
        screen.addMessage("Remove First Text");
        screen.printScreen();


        while(!Bot_2.stop){

            screen.addMessage(message("Add Message:"));
            screen.printScreen();

        }


        while (true){
            System.out.println("Lover Bot is on break, chat with his boring sibling");
            screen.printScreen();
            screen.botMessage(message("Ask question:"));

        }



    }
}





public class Main {

    public static void main(String[] args) {
        LoveBot lb = new LoveBot();
        lb.start();
    }
}

