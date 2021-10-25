#include<stdio.h>
#include <string.h>


int main(int argc, char const *argv[])
{
    int tablica_przejsc[17][7][2] = {
        {{1,0},{3,0},{5,0},{7,0},{10,0},{12,0},{14,0}},
        {{2}  ,{}   ,{}   ,{}   ,{}    ,{}    ,{}},
        {{9}  ,{}   ,{}   ,{}   ,{}    ,{}    ,{}},
        {{}   ,{4}  ,{}   ,{}   ,{}    ,{}    ,{}},
        {{}   ,{9}  ,{}   ,{}   ,{}    ,{}    ,{}},
        {{}   ,{}   ,{6}  ,{}   ,{}    ,{}    ,{}},
        {{}   ,{}   ,{9}  ,{}   ,{}    ,{}    ,{}},
        {{}   ,{}   ,{}   ,{8}  ,{}    ,{}    ,{}},
        {{}   ,{}   ,{}   ,{9}  ,{}    ,{}    ,{}},
        {{9}  ,{9}  ,{9}  ,{9}  ,{9}   ,{9}   ,{9}},
        {{}   ,{}   ,{}   ,{}   ,{11}  ,{}    ,{}},
        {{}   ,{}   ,{}   ,{}   ,{16}  ,{}    ,{}},
        {{}   ,{}   ,{}   ,{}   ,{}    ,{13}  ,{}},
        {{}   ,{}   ,{}   ,{}   ,{}    ,{16}  ,{}},
        {{}   ,{}   ,{}   ,{}   ,{}    ,{}    ,{15}},
        {{}   ,{}   ,{}   ,{}   ,{}    ,{}    ,{16}},
        {{16} ,{16} ,{16} ,{16} ,{16}  ,{16}  ,{16}}
    };
    // tablica odpowiadająca za przechowywanie listy aktualnych stanów
    int tablica_stanow[100]= {0};
    // pomocnicza zmienna przechowująca iterator ile jest aktualnie dzialajacych stanow do analaizy
    int ile_aktywnych_stanow = 0;

    // tablica char potrzebna do wyświetlania przejsc
    char historia_przejsc[100][300];
    for (int j = 0; j < 100; j++)
    {
        strcpy(historia_przejsc[j],"");
    }
    char slowo[100];
    

    // wczytywanie pliku
    printf("Podaj nazwę pliku: ");
    char nazwa_pliku[20];
    scanf("%s",&nazwa_pliku);
    
    FILE *f = fopen(nazwa_pliku,"r");
    if(f == NULL){
        printf("Blad podczas wczytywania pliku!\n");
        return -1;
    }
    char ch;


    printf("rozpoczynam wczytywanie z pliku: \n");
    while((ch = fgetc(f)) != EOF){
        ile_aktywnych_stanow++;

        // switch do tworzenia histori przejsc i zapamietywania slowa w celu pozniejszego wyprintowania
        switch (ch)
        {
        case '0':
            strcat(slowo,"0");
            strcat(historia_przejsc[0]," -> 0 -> q0");
            break;
        case '1':
            strcat(slowo,"1");
            strcat(historia_przejsc[0]," -> 1 -> q0");
            break;
        case '2':
            strcat(slowo,"2");
            strcat(historia_przejsc[0]," -> 2 -> q0");
            break;
        case '3':
            strcat(slowo,"3");
            strcat(historia_przejsc[0]," -> 3 -> q0");
            break;
        case 'a':
            strcat(slowo,"a");
            strcat(historia_przejsc[0]," -> a -> q0");
            break;
        case 'b':
            strcat(slowo,"b");
            strcat(historia_przejsc[0]," -> b -> q0");
            break;
        case 'c':
            strcat(slowo,"c");
            strcat(historia_przejsc[0]," -> c -> q0");
            break;

        default:
            break;
        }
        for (int i = 0; i < ile_aktywnych_stanow; i++)
        {
            // if do rozpoznawania znaku na wejsciu i przejściu do odpowiedniego dla niego stanu 
            if(ch == '0'){
                if(tablica_przejsc[tablica_stanow[i]][0][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][0][0];
                    char temp[10];
                    sprintf(temp," -> 0 -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == '1')
            {
                 if(tablica_przejsc[tablica_stanow[i]][1][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][1][0];
                    char temp[10];
                    sprintf(temp," -> 1 -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == '2')
            {
               if(tablica_przejsc[tablica_stanow[i]][2][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][2][0];
                    char temp[10];
                    sprintf(temp," -> 2 -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == '3')
            {
                if(tablica_przejsc[tablica_stanow[i]][3][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][3][0];
                    char temp[10];
                    sprintf(temp," -> 3 -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == 'a')
            {
                if(tablica_przejsc[tablica_stanow[i]][4][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][4][0];
                    char temp[10];
                    sprintf(temp," -> a -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == 'b')
            {
               if(tablica_przejsc[tablica_stanow[i]][5][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][5][0];
                    char temp[10];
                    sprintf(temp," -> b -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch == 'c')
            {
               if(tablica_przejsc[tablica_stanow[i]][6][0]!=0){
                    tablica_stanow[i] = tablica_przejsc[tablica_stanow[i]][6][0];
                    char temp[10];
                    sprintf(temp," -> c -> q%d",tablica_stanow[i]);
                    strcat(historia_przejsc[i+1],temp);
                }
            }
            else if (ch=='#')
            {
                // printowanie tablicy przejsc w ładny sposób
                int czy_zakonczyl_w_stanie_akceptujacym = 0;
                for (int j = 0; j < ile_aktywnych_stanow; j++)
                {
                    printf("q0");
                    if(tablica_stanow[j]!=0)
                    {
                        if (czy_zakonczyl_w_stanie_akceptujacym==0&&tablica_stanow[j]==9)
                        {
                            czy_zakonczyl_w_stanie_akceptujacym=1;
                        }
                        else if (czy_zakonczyl_w_stanie_akceptujacym==0&&tablica_stanow[j]==16)
                        {
                            czy_zakonczyl_w_stanie_akceptujacym=2;
                        }
                    }   
                    printf("%s\n",  historia_przejsc[j]);                   
                }
                if(czy_zakonczyl_w_stanie_akceptujacym ==1)
                    printf("Dla slowa %s automat osiagnal stan akceptujacy dla potrojenia cyfr q9\n", slowo);
                else if (czy_zakonczyl_w_stanie_akceptujacym==2)
                    printf("Dla slowa %s automat osiagnal stan akceptujacy dla potrojenia liter q16\n", slowo);
                else
                    printf("Dla slowa %s automat nie osiagnal stanu akceptujacego\n", slowo);
                strcpy(slowo,"");
                printf("\n");

                // reset potrzebnych tablic przed nastepnym slowem 
                ile_aktywnych_stanow=0;
                for (int j = 0; j < 100; j++)
                {
                    tablica_stanow[j]=0;
                    strcpy(historia_przejsc[j],"");
                }
            }
            else
            {
                fclose(f);
                printf("blad czytania znakow z pliku");
                return -1;
            }
        }
    }
    fclose(f);
    return 0;
}
