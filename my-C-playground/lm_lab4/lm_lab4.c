#include<stdio.h>
#include <string.h>
#include <stdlib.h>
#include <regex.h>

int main(int argc, char const *argv[])
{
    // deklaracja zmiennych potrzebnych do wykonania sprawdzenia z wyrażeniem regularnym
    regex_t regularneWyrazenie;
    int zwrotCzyRegularneWyrazenieJestOk;
    /* kompilacja wyrazenia regularnego w celu sprawdzenia czy jest ono zgodne ze standardami */
    zwrotCzyRegularneWyrazenieJestOk = 
      regcomp(&regularneWyrazenie, 
      "^([0]|([1-9][0-9]*))[/+/*///^-]([0]|([1-9][0-9]*))([/+/*///^-]([0]|([1-9][0-9]*)))*(;([0]|([1-9][0-9]*))[/+/*///^-]([0]|([1-9][0-9]*))([/+/*///^-]([0]|([1-9][0-9]*)))*)*$"
      , REG_EXTENDED);

    // jeżeli nie udalo sie skompilowac wyrazenia regularnego to zostanie wyrzucony błąd
    if (zwrotCzyRegularneWyrazenieJestOk) {
        printf("Nie udalo sie skompilowac wyrazenia regularnego");
        return -1;
    }

    // wprowadzanie zmiennych przez użytkownika
    printf("Podaj wyrazenie do sprawdzenia: ");
    char wyrazenie[300];
    scanf("%s", wyrazenie);
    
    /* wykonanie sprawdzenia podanego przez uzytkownika wyrazenia pod kątem zgodności z wyrażeniem regularnym */
    zwrotCzyRegularneWyrazenieJestOk = regexec(&regularneWyrazenie, wyrazenie, 0, NULL, 0);
    if (!zwrotCzyRegularneWyrazenieJestOk) {
        printf("%s wyrażenie jest zgodne z gramatyką\n",wyrazenie);
    }
    else if (zwrotCzyRegularneWyrazenieJestOk == REG_NOMATCH) {
        printf("%s wyrazenie nie jest zgodne z gramatyka\n",wyrazenie);
    }
    else {
        //jezeli wystapil blad podczas sprawdzania to program zakonczy działanie ze zwrotem -1
        regerror(zwrotCzyRegularneWyrazenieJestOk, &regularneWyrazenie, wyrazenie, sizeof(wyrazenie));
        printf("Blad podczas sprawdzania zgodnosci z wyrazeniem regularnym\n");
        return -1;
    }

    /* Zwalanianie pamieci zadeklarowanej na potrzeby wyrazenia regularnego*/
    regfree(&regularneWyrazenie);
    return 0;
}
