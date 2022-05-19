package pt.c40task.l05wumpus;
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
   private static Printer printer = null;

   public static void main(String[] args) {
	   int caveDimension = 4, roomDimension = 4;
	   AppWumpus.montadorDaCaverna = new MontadorDaCaverna(caveDimension, roomDimension);
	   
	   AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }

   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
	  
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      String cave[][] = tk.retrieveCave();
	  String[] messages;
      AppWumpus.montadorDaCaverna.buildCaveByCSVFileContent(cave);
      if(!AppWumpus.montadorDaCaverna.isCaveBuildedValid()) {
          System.out.println("Configuracoes invalidas foram passadas na construcao da caverna");
          System.out.println("Infelizmente nao poderemos seguir com o jogo...");
          return;
       }

      if (arquivoMovimentos == null) { // modo interativo
          String playerName = AppWumpus.getPlayerName();
          AppWumpus.controleDoJogo = new ControleDoJogo(playerName, AppWumpus.montadorDaCaverna.getHeroi(), AppWumpus.montadorDaCaverna.getWumpus());
          AppWumpus.printer = new Printer(AppWumpus.montadorDaCaverna.getMapa());
          
          tk.writeBoard(AppWumpus.printer.getMapa(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus()); //inicial
          
          String command;
          do {
        	  command = AppWumpus.keyboard.nextLine();
        	  messages = AppWumpus.controleDoJogo.gameTurn(command.charAt(0));
        	  
              tk.writeBoard(AppWumpus.printer.getMapa(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus()); // a cada movimento
              AppWumpus.printer.turnPrinter(AppWumpus.controleDoJogo.getName(), AppWumpus.controleDoJogo.getScore(), messages);
          } while (!AppWumpus.controleDoJogo.winnableGame());
          
      } else { // modo automático
          AppWumpus.controleDoJogo = new ControleDoJogo("Alcebiades", AppWumpus.montadorDaCaverna.getHeroi(), AppWumpus.montadorDaCaverna.getWumpus());
          AppWumpus.printer = new Printer(AppWumpus.montadorDaCaverna.getMapa());
          String movements = tk.retrieveMovements();
          
          tk.writeBoard(AppWumpus.printer.getMapa(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus()); // inicial
          
          for (int j = 0; j < movements.length(); j++) {
             messages = AppWumpus.controleDoJogo.gameTurn(movements.charAt(j));
             tk.writeBoard(AppWumpus.printer.getMapa(), AppWumpus.controleDoJogo.getScore(), AppWumpus.controleDoJogo.getStatus()); // a cada movimento
          }
      }
      tk.stop();
   }

   private static String getPlayerName() {
      System.out.println("Bem-vinde ao Mundo de Wumpus!!");
      System.out.println("Digite seu nome, por favor:");
      return AppWumpus.keyboard.nextLine();
   }
}
