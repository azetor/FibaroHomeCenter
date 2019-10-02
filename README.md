# FibaroHomeCenter

Opis zadania:
Zadaniem jest napisanie aplikacji Android współpracującej z centralami Home Center 2 (w wersji
3.xxx).
W ramach zadania należy utworzyć aplikację typu na Android API Level 16+.
Aplikacja powinna wyglądać poprawnie na telefonach oraz tabletach.
Aplikacja powinna być rozwijana na prywatnym repozytorium BitBucket.
Po zakończeniu zadania należy nadać prawa do wglądu dla konta „fibar_group”.
Dane połączeniowe do testowej centralki:
adres: http://styx.fibaro.com:9999
login: admin
pass: admin
Połączenie można przetestować logując się bezpośrednio na podany wyżej adres, gdzie znajduje
się interfejs konfiguracyjny lub wywołując w przeglądarce link:
http://styx.fibaro.com:9999/api/settings/info, w odpowiedzi na którego centralka powinna
odpowiedzieć JSON-em zawierającym podstawowe informacje.
Wymagania:
1) Pobranie i prezentacja podstawowych informacji o centrali:
• numer seryjny
• adres mac
• numer wersji oprogramowania
2) Pobranie i prezentacja konfiguracji domu:
• pobranie zdefiniowanych sekcji i zawartych w nich pokoi
• pobranie urządzeń znajdujących się w pokojach (wybrane typy: binary_light, dimmable_light)
• ekran prezentujący sekcje z możliwością wyświetlenia pokoi w danej sekcji
• ekran prezentujący pokoje w danej sekcji z możliwością wyświetlenia urządzeń w danym pokoju
• ekran prezentujący urządzenia w danym pokoju
• pobranie i prezentacja stanu urządzeń
3) Umożliwienie sterowania urządzeniami:
• włączenie/wyłączenie moduł w binary_light
• włączenie/wyłączenie/ustawienie wartości modułów dimmable_light
4)* Mile widziane testy jednostkowe.
Opis komunikacji:
1) Komunikacja z centralą HC2 odbywa się przez protokoł HTTP.
2) Każde zapytanie (chyba, że powiedziane jest inaczej) wymaga uwierzytelnienia metodą Basic
Authentication.
3)Odpowiedzi generowane przez webservice mogą zawierać więcej pól niż przykłady
zamieszczone poniżej – dodatkowe informacje, które nie są wymagane do realizacji zadania
należy zignorować
4) Dane reprezentowane są w formacie JSON.
API:
/api/settings/info (nie wymaga uwierzytelniania)
GET
W odpowiedzi zwracane są podstawowe informacje o centrali, np.:
{
"serialNumber": "HC2-005587",
"mac": "38:60:77:6a:c0:8a",
"softVersion": "3.550",
"beta": true,
"zwaveVersion": "3.42",
"serverStatus": 1372264253,
"defaultLanguage": "pl",
"sunsetHour": "21:58",
"sunriseHour": "05:17",
"hotelMode": false,
"updateStableAvailable": false,
"updateBetaAvailable": false,
"batteryLowNotification": true
}
/api/sections
GET
W odpowiedzi zwracane są wszystkie zdefiniowane sekcje, np.:
[{
"id": 1,
"name": "Ground floor",
"sortOrder": 1
}, {
"id": 2,
"name": "First floor",
"sortOrder": 2
}]
/api/rooms
GET
W odpowiedzi zwracane są wszystkie zdefiniowane pokoje. Pole sectionID zawiera identyfikator
sekcji w której dany pokój się znajduje.
[{
"id": 2,
"name": "Kitchen",
"sectionID": 1,
"sortOrder": 14
}, {
"id": 3,
"name": "Living room",
"sectionID": 1,
"sortOrder": 2
}]
/api/devices
GET
W odpowiedzi zwracane są wszystkie zdefiniowane urządzenia. Pole roomID zawiera identyfikator
pokoju w którym dane urządzenie się znajduje. Typ urządzenia jest określony w polu type (należy
z listy urządzeń wziąć dalej pod uwagę jedynie te, które posiadają typ binary_light lub
dimmable_light).
Stan urządzenia jest zawarty w polu properties.value, które dla typu binary_light przyjmuje wartości
0 lub 1 (odpowiednio wyłączony i włączony), a dla typu dimmable_light mieści się w zakresie od 0
do 100.
[{
"id": 3,
"name": "Living room",
"sectionID": 1,
"sortOrder": 2,
"name": "Lampka",
"roomID": 2,
"type": "binary_light",
"properties": {
"dead": "0",
"disabled": "0",
"value": "1"
},
"actions": {
"setValue": 1,
"turnOff": 0,
"turnOn": 0
},
"sortOrder": 2
}, {
"id": 105,
"name": "TV",
"roomID": 1,
"type": "dimmable_light",
"properties": {
"dead": "0",
"disabled": "0",
"value": "100"
},
"actions": {
"setValue": 1,
"turnOff": 0,
"turnOn": 0
},
"sortOrder": 1
}]
/api/callAction
GET
Parametry:
deviceID – numer id urządzenia name – nazwa akcji
arg1, arg2, arg3 – argumenty wywołania akcji
Zapytanie powoduje wysłanie akcji do urządzenia.
W celu włączenia urządzenia należy do niego wysłać bezparametrową akcję „turnOn”.
Dla przykładu dla urządzenia o id=15 całe wywołanie będzie miało postać:
GET /api/callAction?deviceID=15&name=turnOn
Analogicznie, dla wyłączenia istnieje akcja „turnOff”:
GET /api/callAction?deviceID=15&name=turnOff
Moduły typu dimmable_light mogą dodatkowo ustawić dowolną wartość w zakresie 0-100.
Służy do tego jednoargumentowa akcja „setValue”.
Przykład ustawiający wartość urządzenia o id=15 na 50%:
GET /api/callAction?deviceID=15&name=setValue&arg1=50
