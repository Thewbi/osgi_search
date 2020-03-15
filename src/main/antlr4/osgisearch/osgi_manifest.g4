grammar osgi_manifest;
		
osgipackage_description
	:
	osgipackage (',' osgipackage)+
	|
	osgipackage
	;
	
osgipackage
	:
	osgipackage_element (';' osgipackage_element)+ 
	| 
	osgipackage_element
	;
	
osgipackage_element 
	: 
	//fqdn ((':')? '=' STRING_LITERAL|version)?
	//fqdn ((':')? '=' version)?
	fqdn COLON? EQUALS STRING_LITERAL
	|
	fqdn COLON? EQUALS NAME
	|
	fqdn COLON? EQUALS version
	|
	fqdn
	;
	
version
	:
	NUMBER ('.' NUMBER)*
	;
	
fqdn
	:
	NAME ('.' NAME)+
	|
	NAME
	;
	
EQUALS : '=';
COLON : ':';

// STRING represents a string value, for example "abc".
// Note that for simplicity, we don't allow escaping double quotes.
STRING_LITERAL
    : 
    '"' ~('"')* '"'
    ;
    
NUMBER
	:
	[0-9]+
	;
    
NAME
    : 
    [_a-zA-Z][_a-zA-Z0-9]*
    ;

// WS represents a whitespace, which is ignored entirely by skip.
WS
    : 
    [ \t\u000C\r\n]+ -> skip
    ;
    
    
//prog:	(expr NEWLINE)* ;
//expr:	expr ('*'|'/') expr
//    |	expr ('+'|'-') expr
//    |	INT
//    |	'(' expr ')'
//    ;
//NEWLINE : [\r\n]+ ;
//INT     : [0-9]+ ;
