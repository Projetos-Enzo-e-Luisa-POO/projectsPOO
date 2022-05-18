package pt.c40task.l05wumpus.configs;

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

    public String getComponentWithHighestPriority(String[] componentes) {
        String componentWithHighestPriority = "Brisa";
        for (int i = 0; i < componentes.length; i++) {
            if ((componentes[i] == "Ouro") || (componentes[i] == "Buraco") || (componentes[i] == "Wumpus")) {
                componentWithHighestPriority = componentes[i];
                break;
            }
            else if (componentes[i] == "Heroi") {
                componentWithHighestPriority = componentes[i];
            }
            else if (componentes[i] == "Fedor" && componentWithHighestPriority == "Brisa") {
                componentWithHighestPriority = componentes[i];
            }
        }
        return componentWithHighestPriority;
    }
}
