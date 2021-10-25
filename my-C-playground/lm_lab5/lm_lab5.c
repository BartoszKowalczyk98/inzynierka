#include<stdio.h>
#include <string.h>
#include <stdlib.h>


// tablice first z gramatyki
char FirstS[]="0123456789(";
char FirstZ[]="0123456789(";
char FirstW[]="0123456789(";
char FirstWp[]="*:+-^";
char FirstP[]="0123456789(";
char FirstR[]="0123456789";
char FirstRp[]=".";
char FirstL[]="0123456789";
char FirstLp[]="0123456789";
char FirstC[]="0123456789";
char FirstO[]="*:+-^";


// symbol wczytany do sprawdzenia
char nextsymbol;

// indeks wskazujacy aktualna pozycje w wyrazeniu
int indexOfWyrazenie = -1;

// tablica char przechowujaca wyrazenie wpisane przez uzytkownika
char wyrazenie[300];



// wczytanie następnego symbolu z wyrażenia
char readNextSymbol();
void checkChar(char c);
void readS();
void readZ();
void readW();
void readWp();
void readP();
void readR();
void readRp();
void readL();
void readLp();
void readC();
void readO();


int main(int argc, char const *argv[])
{
    // wprowadzanie zmiennych przez użytkownika
    printf("Podaj wyrazenie do sprawdzenia: ");
    scanf("%s", wyrazenie);

    printf("Podane wyrazenie: %s\n\n", wyrazenie);
	nextsymbol = readNextSymbol();
	readS();
	if (nextsymbol != '\0')
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    return -1;
    }
	else
		printf("Wyraznie jest zgodne z gramatyka\n");
	return 0;
}


char readNextSymbol()
{
    return wyrazenie[++indexOfWyrazenie];
}

void checkChar(char c)
{
	if (nextsymbol == c)
		nextsymbol = readNextSymbol();
	else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
		
}

// S::= W ; Z
void readS(){
    
    if (strchr(FirstW, nextsymbol) != NULL && nextsymbol!='\0')
    {
        readW();
        checkChar(';');
        readZ();
    }
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
    
}
// Z::= W ; Z | eps
void readZ()
{
	if (strchr(FirstW, nextsymbol) != NULL && nextsymbol != '\0')
	{
		readW();
		checkChar(';');
		readZ();
	}
	
}
// W::= P W’
void readW()
{
	if (strchr(FirstP, nextsymbol) != NULL && nextsymbol != '\0')
	{
		readP();
		readWp();
	}
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
}
// W’::= O W | eps
void readWp()
{
	if (strchr(FirstO, nextsymbol) !=NULL && nextsymbol != '\0')
	{
		readO();
		readW();
	}
	
}
// P::= R | ( W )
void readP()
{
	if (strchr(FirstR, nextsymbol) !=NULL && nextsymbol != '\0')
	{
		readR();
	}
	else if (nextsymbol == '(')
	{
		checkChar('(');
		readW();
		checkChar(')');
	}
}
// R::= L R’
void readR()
{
	if (strchr(FirstL, nextsymbol) !=NULL && nextsymbol != '\0')
	{
		readL();
		readRp();
	}
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
}
// R’::= . L | eps
void readRp()
{
	if (nextsymbol == '.')
	{
		checkChar('.');
		readL();
	}

}
// L::= C L’
void readL()
{
	if (strchr(FirstC, nextsymbol) != NULL && nextsymbol !='\0')
	{
		readC();
		readLp();
	}
	else// wyrzucenie błędu i zakończenie programu
	{
		printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
	}
}
// L’::= L | eps
void readLp()
{
    if (strchr(FirstL, nextsymbol) != NULL && nextsymbol !='\0')
	{
		readL();
	}
	
}
// C::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
void readC()
{
	if(nextsymbol == '0'){
        checkChar('0');
    }
    else if(nextsymbol == '1')
    {
        checkChar('1');
    }
    else if(nextsymbol == '2')
    {
        checkChar('2');
    }
    else if(nextsymbol == '3')
    {
        checkChar('3');
    }
    else if(nextsymbol == '4')
    {
        checkChar('4');
    }
    else if(nextsymbol == '5')
    {
        checkChar('5');
    }
    else if(nextsymbol == '6')
    {
        checkChar('6');
    }
    else if(nextsymbol == '7')
    {
        checkChar('7');
    }
    else if(nextsymbol == '8')
    {
        checkChar('8');
    }
    else if(nextsymbol == '9')
    {
        checkChar('9');
    }
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
}
// O::= * | : | + | - | ^
void readO()
{
    if(nextsymbol == '+'){
        checkChar('+');
    }
    else if(nextsymbol == ':')
    {
        checkChar(':');
    }
    else if(nextsymbol == '*')
    {
        checkChar('*');
    }
    else if(nextsymbol == '-')
    {
        checkChar('-');
    }
    else if(nextsymbol == '^')
    {
        checkChar('^');
    }
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }

}
