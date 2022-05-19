package pt.c40task.l05wumpus;
import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * 
 * @author ra247069, ra247249
 * --
 */

public class AppWumpus {

   private static Scanner keyboard = new Scanner(System.in);
   private static MontadorDaCaverna montadorDaCaverna = null;
   private static ControleDoJogo controleDoJogo = null;

   public static void main(String[] args) {

      int caveDimension = 4, roomDimension = 4;
      AppWumpus.montadorDaCaverna = new MontadorDaCaverna(caveDimension, roomDimension);

      String playerName = AppWumpus.getPlayerName();
      int[] initialHeroPosition = {0,0};
      Heroi hero = new Heroi(AppWumpus.montadorDaCaverna.getCaverna(), initialHeroPosition, 4, 2);
      AppWumpus.controleDoJogo = new ControleDoJogo(playerName, hero);

      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }

   private static String getPlayerName() {
      System.out.println("Bem-vinde ao Mundo de Wumpus!!");
      System.out.println("Digite seu nome, por favor:");
      return AppWumpus.keyboard.nextLine();
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      String cave[][] = tk.retrieveCave();
      
      AppWumpus.montadorDaCaverna.buildCaveByCSVFileContent(cave);

      if(!AppWumpus.montadorDaCaverna.isCaveBuildedValid()) {
         System.out.println("Configuracoes invalidas foram passadas na construcao da caverna");
         System.out.println("Infelizmente nao poderemos seguir com o jogo...");
         return;
      }

      System.out.println("=== Caverna");
      // função de impressão da caverna + nome Player + score + mensagem opcional
      
      String movements = tk.retrieveMovements();
      for (int j = 0; j < movements.length(); j++) {
         AppWumpus.controleDoJogo.handleMovementCommand(movements.charAt(j));
         // função de impressão da caverna + nome Player + score + status + mensagem opcional
         //obs -> status = 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      }
      
      tk.writeBoard(AppWumpus.controleDoJogo.getCave(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus());
      
      tk.stop();
   }

   public static void executaJogoInterativo(String arquivoCaverna, String arquivoSaida) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, null);
      
      String cave[][] = tk.retrieveCave();
      
      AppWumpus.montadorDaCaverna.buildCaveByCSVFileContent(cave);

      if(!AppWumpus.montadorDaCaverna.isCaveBuildedValid()) {
         System.out.println("Configuracoes invalidas foram passadas na construcao da caverna");
         System.out.println("Infelizmente nao poderemos seguir com o jogo...");
         return;
      }

      System.out.println("=== Caverna");
      // função de impressão da caverna + nome Player + score + mensagem opcional
      
      String command;
      do {
         command = AppWumpus.keyboard.nextLine();
         AppWumpus.controleDoJogo.handleMovementCommand(command.charAt(0));
         // função de impressão da caverna + nome Player + score + status + mensagem opcional
         //obs -> status = 'w' para venceu; 'n' para perdeu; 'x' intermediárias
      } while(AppWumpus.controleDoJogo.getStatus() != "Fim");
      //como vamos identificar o fim do jogo? pensei em criar uma flag "status" no controleDoJogo, que vai valer "Fim" ou algo do tipo quando acabar
      
      tk.writeBoard(AppWumpus.controleDoJogo.getCave(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus());
      
      tk.stop();
   }
}
