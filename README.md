# API do obsługi usług banku PEKAO SA

## 1. Wymagania
### 1.1. Środowisko wykonawcze
Aby korzystać z API niezbędny będzie:
- Maven > 3
- Java > 11
- SpringBoot > 2.5.6

### 1.2. Konfiguracja
Przykładowa konfiguracja wymagana do uwierzytelnienia użytkownika znajduje się w pliku [credentials.properties](./src/main/resources/credentials.properties). Plik ten należy dodać w projekcie który implementuje interface API.

[Credentials](./src/main/java/pl/pekao24/api/Credentials.java) odpowiada za mapowanie parametrów konfiguracyjnych które dostępne będą z poziomu aplikacji.

### 1.3. Autoryzacja
Do utworzenia sesji z bankiem potrzebne są dostępy użytkownika który posiada konto w banku PEKAO SA. Dostępy to login i hasło do bankowości internetowej.

## 2. Kompilacja
Pobierz źródła z repozytorium kodu: 
```
git clone https://github.com/szczepix1983/pekao-api.git
```
Przekompiluj źródła do lokalnego repo Maven-a
```
mvn deploy
```

## 3. Użycie we własnym projekcie
Biblioteka dostępna będzie w lokalnym repo Maven-a, należy ją załączyć do projektu jako poniższa zależność 
```
<dependency>
    <groupId>pl.pekao24.api</groupId>
    <artifactId>pekao24-api</artifactId>
    <version>${version.pekao24}</version>
</dependency>
```
Aby SpringBoot wykrył zależności oraz wczytał poprawnie konfigurację biblioteki należy zainicjować aplikację w poniższy sposób
```
@SpringBootApplication(scanBasePackages={"pl.pekao24.api", "com.twoja.paczka"})
@EnableConfigurationProperties(Credentials.class)
```
Do uwierzytelnienia używane są parametry konfiguracyjne z klasy [Credentials](./src/main/java/pl/pekao24/api/Credentials.java). 

Aby utworzyć sesję z bankiem należy wstrzyknąć [AuthService](./src/main/java/pl/pekao24/api/auth/AuthService.java) wywołując interface `authorize()`.

Przykład uwierzytelnienia użytkownika
```
boolean status = this.authService.authorize(
                    this.credentials.getLogin(),
                    this.credentials.getPassword());
```
Po nawiązaniu sesji z bankiem można korzytać z usług dostępnych w paczce `pl.pekao24.api.domains`.  

## 4. Ograniczenia
Projekt wykorzystuje dostępy do bankowości internetowej w celu nawiązania sesji z bankiem, umożliwia to dalsze korzystanie z API w celu pozyskania podstawowych informacji nt. uwierzytelnionego użytkownika tj. historia transakcji, dane personalne, informacje dot. konta czy produktów bankowych. Dostępne są funkcje które udostępnia bank przez co biblioteka nie może dostarczać innych funkcjonalności. 

## 5. Podsumowanie
Biblioteka jest Open Source, w przypadku jakichkolwiek obaw proszę o zapoznanie się ze źródłami. Projekt jest w fazie BETA. 

Nie należy publikować projektu w którym użyte są dostępy do konta bankowego na publicznych repozytoriach tj. github (należy pamiętać o dodaniu `credentials.properties` do ignore).

Bezwględnie, nie należy używać biblioteki w celach niezgodnych z prawem np. do ataków DDOS czy innego typu zagrożeń sieciowych które mogłyby zagrozić prawidlowemu działaniu banku PEKAO SA - biblioteka do użytku tylko i wyłącznie na potrzeby klienta banku.