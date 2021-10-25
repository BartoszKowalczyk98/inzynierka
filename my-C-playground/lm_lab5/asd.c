#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif

#include <stdio.h>
#include<string.h>

char expression[100];
int helpIndex = -1;
char current;

//zbiory pierwszych dla kazdej produkcji (z pominieciem eps, bo jest on obslugiwany nizej)
char firstS[] = "0123456789(";
char firstZ[] = "0123456789(";
char firstW[] = "0123456789(";
char firstWp[] = "*:+-^";
char firstP[] = "0123456789(";
char firstR[] = "0123456789";
char firstRp[] = ".";
char firstL[] = "0123456789";
char firstLp[] = "0123456789";
char firstC[] = "0123456789";
char firstO[] = "*:+-^";

//funkcja do wychodzenia z programu w przypadku niezgodnosci symbolu z gramatyka
void propagateError()
{
	printf("Wyrazenie niezgodne z gramatyka");
	exit(-1);
}

//wczytywanie nastepnego symbolu
char nextSymbol()
{
	helpIndex++;
	printf("Analizowany symbol: %c\n", expression[helpIndex]);
	return expression[helpIndex];
}

void load(char symbol)
{
	if (current == symbol)
		current = nextSymbol();
	else
		propagateError();
}

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



int main(void)
{
	//wpisywanie wyrazenia z klawiatury
	//format: wyrazenie bez spacji, na przyklad: (1.2*3)+5-(23.4+3)^3;8:3;
	printf("Podaj wyrazenie: ");
	scanf("%s", expression);

	printf("Podane wyrazenie: %s\n\n", expression);
	//printf("%d", helpIndex);

	current = nextSymbol();
	readS();
	if (current != '\0')
		propagateError();
	else
		printf("Wyraznie jest zgodne z gramatyka\n");

	return 1;
}

void readS()
{
	if (strchr(firstW, current))
	{
		readW();
		load(';');
		readZ();
	}
	else
		propagateError();
}

void readZ()
{
	if (strchr(firstW, current) && current != '\0')
	{
		readW();
		load(';');
		readZ();
	}
	else
	{
		//epsilon
	}
}

void readW()
{
	if (strchr(firstP, current) && current != '\0')
	{
		readP();
		readWp();
	}
	else
		propagateError();
}

void readWp()
{
	if (strchr(firstO, current) && current != '\0')
	{
		readO();
		readW();
	}
	else
	{
		//epsilon
	}
}

void readP()
{
	if (strchr(firstR, current) && current != '\0')
	{
		readR();
	}
	else if (current == '(')
	{
		load('(');
		readW();
		load(')');
	}
}

void readR()
{
	if (strchr(firstL, current) && current != '\0')
	{
		readL();
		readRp();
	}
	else
		propagateError();
}

void readRp()
{
	if (current == '.')
	{
		load('.');
		readL();
	}
	else
	{
		//eps
	}
}

void readL()
{
	if (strchr(firstC, current) && current != '\0')
	{
		readC();
		readLp();
	}
	else
	{
		propagateError();
	}
}

void readLp()
{
	if (strchr(firstL, current) && current != '\0')
	{
		readL();
	}
	else
	{
		//eps
	}
}

void readC()
{
	switch (current)
	{
	case '0':
		load('0');
		break;
	case '1':
		load('1');
		break;
	case '2':
		load('2');
		break;
	case '3':
		load('3');
		break;
	case '4':
		load('4');
		break;
	case '5':
		load('5');
		break;
	case '6':
		load('6');
		break;
	case '7':
		load('7');
		break;
	case '8':
		load('8');
		break;
	case '9':
		load('9');
		break;
	default:
		propagateError();
		break;
	}
}

void readO()
{
	switch (current)
	{
	case '+':
		load('+');
		break;
	case '-':
		load('-');
		break;
	case '*':
		load('*');
		break;
	case ':':
		load(':');
		break;
	case '^':
		load('^');
		break;
	default:
		propagateError();
		break;
	}
}