package pt.c40task.l05wumpus;

public class ComponentDescriptionController {
    
    public char convertToCharacter(String componentDescription) {
        switch(componentDescription) {
            case "Heroi":
                return 'P';
            case "Wumpus":
                return 'W';
            case "Buraco":
                return 'B';
            case "Ouro":
                return 'O';
            case "Fedor":
                return 'f';
            case "Brisa":
                return 'b';
            default:
                return '#';
        }
    }
}
