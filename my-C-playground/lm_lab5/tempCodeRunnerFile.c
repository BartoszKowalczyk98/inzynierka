#include<stdio.h>
#include <string.h>
#include <stdlib.h>


// tablice first z gramatyki
char FirstS[]={'0','1','2','3','4','5','6','7','8','9','('};
char FirstZ[]={'0','1','2','3','4','5','6','7','8','9','('};
char FirstW[]={'0','1','2','3','4','5','6','7','8','9','('};
char FirstWp[]={'*' , ':' , '+' , '-' , '^'};
char FirstP[]={'0','1','2','3','4','5','6','7','8','9','('};
char FirstR[]={'0','1','2','3','4','5','6','7','8','9'};
char FirstRp[]={'.'};
char FirstL[]={'0','1','2','3','4','5','6','7','8','9'};
char FirstLp[]={'0','1','2','3','4','5','6','7','8','9'};
char FirstC[]={'0','1','2','3','4','5','6','7','8','9'};
char FirstO[]={'*',':','+','-','^'};



// symbol wczytany do sprawdzenia
char next;

// indeks wskazujacy aktualna pozycje w wyrazeniu
int i = -1;

// tablica char przechowujaca wyrazenie wpisane przez uzytkownika
char wyrazenie[300];



// wczytanie następnego symbolu z wyrażenia
char readNext();
void read(char c);
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
	next = readNext();
	readS();
	if (next != '\0')
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    return -1;
    }
	else
		printf("Wyraznie jest zgodne z gramatyka\n");
	return 1;
}


char readNext()
{
    return wyrazenie[++i];
}

void read(char c)
{
	if (next == c)
		next = readNext();
	else// wyrzucenie błędu i zakończenie programu
    {
        printf("doopsko");
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }
		
}

// S::= W ; Z
void readS(){
    
    if (strchr(FirstW, next) != NULL && next!='\0')
    {
        readW();
        read(';');
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
	if (strchr(FirstW, next) != NULL && next != '\0')
	{
		readW();
		read(';');
		readZ();
	}
	
}
// W::= P W’
void readW()
{
	if (strchr(FirstP, next) != NULL && next != '\0')
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
	if (strchr(FirstO, next) !=NULL && next != '\0')
	{
		readO();
		readW();
	}
	
}
// P::= R | ( W )
void readP()
{
	if (strchr(FirstR, next) !=NULL && next != '\0')
	{
		readR();
	}
	else if (next == '(')
	{
		read('(');
		readW();
		read(')');
	}
}
// R::= L R’
void readR()
{
	if (strchr(FirstL, next) !=NULL && next != '\0')
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
	if (next == '.')
	{
		read('.');
		readL();
	}

}
// L::= C L’
void readL()
{
	if (strchr(FirstC, next) != NULL && next !='\0')
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
    if (strchr(FirstL, next) != NULL && next !='\0')
	{
		readL();
	}
	
}
// C::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
void readC()
{
	if(next == '0'){
        read('0');
    }
    else if(next == '1')
    {
        read('1');
    }
    else if(next == '2')
    {
        read('2');
    }
    else if(next == '3')
    {
        read('3');
    }
    else if(next == '4')
    {
        read('4');
    }
    else if(next == '5')
    {
        read('5');
    }
    else if(next == '6')
    {
        read('6');
    }
    else if(next == '7')
    {
        read('7');
    }
    else if(next == '8')
    {
        read('8');
    }
    else if(next == '9')
    {
        read('9');
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
    if(next == '+'){
        read('+');
    }
    else if(next == ':')
    {
        read(':');
    }
    else if(next == '*')
    {
        read('*');
    }
    else if(next == '-')
    {
        read('-');
    }
    else if(next == '^')
    {
        read('^');
    }
    else// wyrzucenie błędu i zakończenie programu
    {
        printf("Podane wyrazenie nie jest zgodne z gramatyka!");
	    exit(-1);
    }

}
