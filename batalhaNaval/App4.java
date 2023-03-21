package batalhaNaval;
import java.util.Scanner;

public class App4 {
    static short QTD_NAVIOS = 5;
    static Jogador player1 = new Jogador(1);
    static Jogador player2 = new Jogador(2);
    public static void main(String[] args) {
        intro();
        //variaveis e objetos
        Scanner input = new Scanner(System.in);
        String msg;
        short tempx, tempy;
        boolean atirou;

        // pergunta as coordenadas
        System.out.println(player1.nome + ", enter your ships' coordinates.");
        for (short x=1; x<=QTD_NAVIOS; ){
            System.out.println("Enter ship " + x + " location > x y <:");
            tempx=input.nextShort();
            tempy=input.nextShort();
            msg = player1.insereCoordenadas(tempx, tempy);
            if (msg != "") System.out.println(msg);
            else x++;
        }
        player1.exibeMapaPlayer();
        aguarde(3);
        limpaTela();

        // pergunta as coordenadas
        System.out.println(player2.nome + ", enter your ships' coordinates.");
        for (short x=1; x<=QTD_NAVIOS; ){
            System.out.println("Enter ship " + x + " location > x y <:");
            tempx=input.nextShort();
            tempy=input.nextShort();
            msg = player2.insereCoordenadas(tempx, tempy);
            if (msg != "") System.out.println(msg);
            else x++;
        }
        player2.exibeMapaPlayer();
        aguarde(3);
        limpaTela();
        
        while(true){
            // player 1 atira
            do{
                System.out.println(player1.nome + ", enter hit row/column:");
                tempx=input.nextShort();
                tempy=input.nextShort();

                atirou = player1.atiraEm(player2, tempx, tempy);
            } while(!atirou);
            // exibe o mapa do player 2
            player2.exibeMapaGame();
            if(player1.ganhou) {
                fimGame(player1.nome);
                break;
            }
            
            // player 2 atira
            do{
                System.out.println(player2.nome + ", enter hit row/column:");
                tempx=input.nextShort();
                tempy=input.nextShort();
                
                atirou = player2.atiraEm(player1, tempx, tempy);
            } while(!atirou);
            // exibe o mapa do player 1
            player1.exibeMapaGame();
            if(player2.ganhou) {
                fimGame(player2.nome);
                break;
            }
        }
        input.close();
    }

    public static void intro(){
        limpaTela();
        System.out.println("\tWelcome to Battleship!!!");
        System.out.println("\n\n\n");
        aguarde(2);
        limpaTela();
    }

    public static void fimGame(String ganhador){
        System.out.println(ganhador + " WINS! You sunk all of your opponent's ships!");
        System.out.println("Map of "+ player1.nome);
        player1.exibeMapaPlayer();
        System.out.println("Map of "+player2.nome);
        player2.exibeMapaPlayer();
    }

    public static void limpaTela(){
        for (short i = 1; i<100; i++) System.out.println("\n");
    }

    public static void aguarde(int segundos){
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
