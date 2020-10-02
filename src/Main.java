import java.util.Random;
import java.util.Scanner;
//level 1 hw4
//без логики
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        playGame(scanner,random);
    }

    static void playGame(Scanner scanner, Random random){
        System.out.println("Введите уровень игры");

        char[][] field = getField(scanner.nextInt());
        drawField(field);
        while (isFullDraw(field)==false){
            doPlayer(field,scanner);
            if(isFinal(field,'X')){
                break;
            };
            if (isFullDraw(field)){
                break;
            }
            doMachine(field,random);
            if(isFinal(field,'O')){
                break;
            };
            drawField(field);
        }
        drawField(field);
    }

    static boolean isWin(char[][] field, char sign){
        int vertical=0;
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if(field[i][j]==sign){
                    vertical++;
                }
            }
            if(vertical==field.length){
                return true;
            }
            vertical=0;
        }
        int goriz=0;
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if(field[j][i]==sign){
                    goriz++;
                }
            }
            if(goriz==field.length){
                return true;
            }
            goriz=0;
        }
        int diag=0;
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if (i==j && field[i][j]==sign){
                    diag++;
                }
            }
        }
        if(diag==field.length){
            return true;
        }
        int diag_poboch=0;
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if (i+j==field.length-1 && field[i][j]==sign){
                    diag_poboch++;
                }
            }
        }
        if(diag_poboch==field.length){
            return true;
        }
        return false;
    }

    static boolean isFinal(char[][] field, char sign){
        if (isWin(field,sign)){
            String name = sign == 'X' ? "Player" : "AI";
            System.out.println("Победил"+name);
            return true;
        }
        return false;
    }

    static boolean isFullDraw(char[][] field){

    for(int i=0;i<field.length;i++){
        for(int j=0;j<field.length;j++){
            if (field[i][j]=='-'){
                return false;
            }
        }
    }
        System.out.println("Ничья");
    return true;
    }

    static void doPlayer(char[][] field, Scanner scanner){
        int x,y;
        do {
            x = getCoordinate(scanner, 'X',field.length);
            y = getCoordinate(scanner, 'Y',field.length);
        }while (field[x][y] != '-');
        field[x][y]='X';
    }

    static void doMachine(char[][] field, Random random){
        int x,y;
        do {
            x = random.nextInt(field.length);
            y = random.nextInt(field.length);
        }while (field[x][y] != '-');
        field[x][y]='O';
    }

    static int getCoordinate(Scanner scanner, char coordinate, int n){
        int coord;
        do {
            System.out.printf("Введите координату для %s", coordinate);
            coord = scanner.nextInt()-1;
        }while(coord<0||coord>n-1);
        return coord;
    }

    static char[][] getField(int n){
        char[][] field = new char[n][n];
        for (int i=0; i<n;i++){
            for (int j=0; j<n;j++){
                field[i][j]='-';
            }
        }
        return field;
    }

    static void drawField(char[][] field){
        for (int i=0; i<field.length;i++){
            for (int j=0; j<field.length;j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }

    }
}
