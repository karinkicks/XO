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
        int x=0,y=0;
        int maxLine=0;
        int maxColumn=0;
        int maxDiagonal=0;
        int maxPobochDiagonal=0;
        int amountOfSigns=0;
        int numberOfPlaceLine=0;
        int numberOfPlaceColumn=0;
        int anySign=0;

        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if(field[i][j]=='X'){
                    amountOfSigns++;
                }
                if(field[i][j] != '-'){
                    anySign++;
                }
            }
            if(amountOfSigns>maxLine && anySign!=field.length){
                maxLine=amountOfSigns;
                numberOfPlaceLine=i;
            }
            amountOfSigns=0;
            anySign=0;
        }
        amountOfSigns=0;
        anySign=0;
        System.out.println("maxLine"+maxLine);

        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if(field[j][i]=='X'){
                    amountOfSigns++;
                }
                if(field[j][i] != '-'){
                    anySign++;
                }
            }
            if(amountOfSigns>maxColumn && anySign!=field.length){
                maxColumn=amountOfSigns;
                numberOfPlaceColumn=i;
            }
            amountOfSigns=0;
            anySign=0;
        }
        System.out.println("maxColumn"+maxColumn);
        amountOfSigns=0;
        anySign=0;
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if(i==j && field[i][j]=='X'){
                    amountOfSigns++;
                }
                if(field[i][j] != '-' && i==j){
                    anySign++;
                }
            }
        }
        if (anySign!=field.length){
            maxDiagonal=amountOfSigns;
        }
        amountOfSigns=0;
        anySign=0;
        System.out.println("maxDiagonal"+maxDiagonal);
        for(int i=0; i<field.length;i++){
            for(int j=0; j<field.length;j++){
                if (i+j==field.length-1 && field[i][j]=='X'){
                    amountOfSigns++;
                }
                if(field[i][j] != '-' && i+j==field.length-1){
                    anySign++;
                }
            }
        }
        if (anySign!=field.length){
            maxPobochDiagonal=amountOfSigns;
        }
        System.out.println("maxPobochDiagonal"+maxPobochDiagonal);
        if(maxLine>=maxColumn && maxLine>=maxDiagonal && maxLine>=maxPobochDiagonal){
            System.out.println(numberOfPlaceLine);
            for(int i=0; i<field.length;i++){
                if(field[numberOfPlaceLine][i] == '-'){
                    x = numberOfPlaceLine;
                    y = i;
                }
            }
            System.out.println("1");
        }

        else if(maxColumn>=maxLine && maxColumn>=maxDiagonal && maxColumn>=maxPobochDiagonal){
            System.out.println(numberOfPlaceColumn);
            for(int i=0; i<field.length;i++){
                if(field[i][numberOfPlaceColumn] == '-'){
                    x = i;
                    y = numberOfPlaceColumn;
                }
            }
            System.out.println("2");
        }

        else if (maxDiagonal>=maxLine && maxDiagonal>=maxColumn && maxDiagonal>=maxPobochDiagonal){
            for(int i=0; i<field.length;i++){
                for(int j=0; j<field.length;j++){
                    if(i==j && field[i][j]=='-'){
                        x = i;
                        y = j;
                        System.out.println(x + " " + y);
                    }
                }
            }
            System.out.println("3");
        }

        else if(maxPobochDiagonal>=maxLine && maxPobochDiagonal>=maxColumn && maxPobochDiagonal>=maxDiagonal){
            for(int i=0; i<field.length;i++){
                for(int j=0; j<field.length;j++){
                    if((i+j==field.length-1) && (field[i][j] == '-')){
                        x = i;
                        y = j;
                        System.out.println(x + " " + y);
                    }
                }
            }
            System.out.println("4");
        }

       else{
        do {
            x = random.nextInt(field.length);
            y = random.nextInt(field.length);
        }while (field[x][y] != '-');
            System.out.println("5");
       }
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
