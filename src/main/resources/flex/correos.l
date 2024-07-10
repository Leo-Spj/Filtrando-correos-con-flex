%option noyywrap
%{
#include<stdio.h>
%}

cuerpo    [a-zA-Z0-9_-]+
dominio   [a-zA-Z0-9]+
extension [a-zA-Z]{2,4}

%%

{cuerpo}@{dominio}\.{extension}   { printf("%s\n", yytext); }
.|\n

%%

int main()
{
    yyin = stdin;
    yylex();
    return 0;
}
