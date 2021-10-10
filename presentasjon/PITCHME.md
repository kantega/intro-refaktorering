---?image=flyt_monster_mork_bakgrunn.svg&size=120%
# Refaktorering

Note:
- Hva forbinder dere med refaktorering eller refaktorisering? (3 min)
- Hva forbinder dere med god kvalitet på kode? (3 min)


---?image=https://img.devrant.com/devrant/rant/r_225313_5QBY7.jpg&size=35%

@snap[north]
### Hva er god kvalitet?
@snapend

Note:
- Bilde wtf/min
- kvalitet er mye forskjellig. TL;DR: du forstår hva meningen er. Ikke bare hva
  som teknisk skjer, men hvorfor og hvilken konsekvens dette har for
  forretningen
- vi får ikke gått gjennom alt i dag, men skal se litt på noen kodeegenskaper som
  påvirker kvalitet


---?image=https://images.pexels.com/photos/7367/startup-photo.jpg

@snap[north-west]

### @color[#27003D](Hvorfor refaktorerer vi?)
@snapend

Note:
- gjøre koden mer lesbar
- betale tilbake teknisk gjeld (fjerne snarveiene)
- tilrettelegge for ny funksjonalitet
- endret forståelse av domenet

---?image=https://images.pexels.com/photos/39900/alarm-clock-clock-time-minute-39900.jpeg&size=100%

@snap[south-west]
### Virkeligheten<br> hindrer oss
@snapend

Note:
- bilde av klokke
- Hva slags utfordringer møter vi i praksis?
- Tid: ofte travelt i prosjekter, vi får ikke alltid gjort de refaktoreringene
  vi ønsker. Kan være pga mye ny funksjonalitet som skal inn, eller gammelt
  system.
- Penger
- Mer konkret:
- manglende tester <-- endring er utrygt
- tester som sementerer implementasjon <-- endring er vanskelig
- Kodekvalitet: navngiving, lange metoder, rammeverk, ulike kodestandarder
- arkitektur: ingen klar tanke, evt feil tanke, ulike tanker blandet sammen
- de overstående tingene er jo det vi vil fikse, men det hindrer oss fra å bare
  sette igang også
- brukere: eksterne er avhengige av systemet, hindrer endring
- kode i stadig endring: vanskelig å gjøre store/inngripende endringer


---?image=https://images.pexels.com/photos/355948/pexels-photo-355948.jpeg

@snap[north]
### Strategier
@snapend

Note:
- leve med det
- gjøre minimale endringer jevnlig for å alltid gjøre verden litt
  bedre, planlegge godt slik at stegene blir små
- beholde grensesnitt, men gjøre store omskrivinger på innsiden
- kast det gamle, skriv nytt
- limited red: lag ny, duplisert funksjonalitet og erstatt etterhvert
- endre API og levere ny ikke-bak-over-kompatibelt system
- presentere noen vanlige refaktoreringer



---?image=https://cdn.aarp.net/content/dam/aarp/health/brain_health/2018/04/1140-name-recall-troubles.imgcache.rev3d7f9d37718cb83ff9ed72a0db9dfb55.jpg

Note:
- bilde navn på postits
- Navngiving: mye viktigere enn du tror

---


@snap[north]
### Beskriv intensjonen i navnet
@snapend

```
//Nope
public Report getReport() {
}

// Better
public Report getExpenditureReport() { }
// or
public Report getFullReportExcludingAccounts() { } 
// or
public Report getFullReportIncludingAccounts() { } 
```

Note:
- hva er inneholdt i variabel eller funksjon? Hva er intensjonen?
- beskriv forskjell på funksjonene i navnet, ikke la det være en overraskelse
- unngå generelle begreper/bruk med forsiktighet (info/data sier ingenting)
- husk at mange slike metoder tar lang tid å sette seg inn i, særlig når
  parameterlisten er lignende i tillegg


---

@snap[north]
### Beslektede navn impliserer slektskap
@snapend

```
public void calculate(int n1, int n2, int n3, int x1, int x2) {
}
```

Note:
- forventer at n1, n2 og n3 er relaterte
- forventer at x1 og x2 er relaterte
- forventer at x-ene og n-ene er forskjellige konsepter
- calculate: hva beregnes? Dette er dårlig navngiving. 


---

@snap[north]
### Navn skal kunne uttales
@snapend

```
//Nope
Date crTs = new Date();

//Better
Date createdTimestamp = new Date();
```

Note:
- vi bruker mer tid på å diskutere og snakke om kode enn å skrive
- IDE-en hjelper deg med lengre navn


---

@snap[north]
### Bruk søkbare navn
@snapend

```
//Nope
String str = "description";

//Better
String courseDescription = "description";
```

Note:
- kodebaser er så store at vi søker etter relaterte begreper
- generelle navn gir mye støy i søkeresultat
- bli enige om hvilke forretningsbegrep som brukes slik at søking blir et
  effektivt verktøy


---

@snap[north]
### Et navn per konsept
@snapend

```
//Nope
public class Movie {
    public List<String> getActors() { }
    public List<String> retrieveGenres() { }
    public String fetchDirector() { }
```

Note:
- et konsept også for tekniske termer
- noen skiller også implementasjonsdetaljer fra forretningslogikk
  (ekstrem-varianten: skiller både på språk og plassering i fil)



---?image=https://images.cdn3.stockunlimited.net/preview1300/mess-of-wires-connecting-computers-and-printers-in-office_1898729.jpg&size=100%

@snap[north-east]
### Funksjoner og klasser
@snapend

Note:
- hva kjennetegner en god funksjon?
- klasser er også veldig likt funksjoner, 


---?image=number_of_lines_in_function.png&size=50%

@snap[north]
### Size matters
@snapend

Note:
- enkleste: antall linjer. Små funksjoner er generelt bra, store er generelt
  dårlige. 
- Hva er stort? Mer enn 20 linjer (ca): kommer an på kontekst
- Jeg har jobbet med en funksjon på 1100 linjer, skyt meg nå. 
- mennesker klarer kun å holde et begrenset antall konsepter i hodet samtidig,
  maks 7. Vi må skrive kode som hjelper utvikleren. 



---

@snap[north]
### Funksjoner har få abstraksjonsnivå
@snapend

```
//Nope
public void handleReport() {
    Report expenseReport = expenseReportsRepository.getLatestReport();
    Mail dailyExpensesMail = new Mail();
    dailyExpensesMail.send();
    mailRepository.save(dailyExpensesMail);
    expenseReport.setStatus(STATUS_PROCESSED);
}


//Better
public void processDailyExpensesReport() {
    Report expenseReport = expenseReportsRepository.getLatestReport();
    sendDailyExpensesMail(expenseReport);
    expenseReport.setStatus(STATUS_PROCESSED);
}
```

Note:
- ikke bland høynivå- og lavnivå-instruksjoner
- ikke bland ulike ansvarsområder (eks mailRepository har ingenting med å
  håndtere daglig rapport å gjøre, men det har med å sende epost å gjøre)
- enda verre tilfelle om det lages sql-statements i stedet for å bare kalle en
  repository-metode
- tenk på hva du ønsker å formidle til brukeren (utvikleren) på dette
  tidspunktet


---

@snap[north]
### Unngå bieffekter
@snapend

```
//Nope
public void sendMail(String from, String to, String text) {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    message.setText(text);
    Transport.send(message);
    redirectUserTo(“http://google.com”);
}
```

Note:
- redirect hører ikke hjemme her
- søkt eksempel? Kanskje. Men denne type ting skjer. 
- Denne er innlysende, men vær obs på mer subtile bieffekter


---?image=https://images.pexels.com/photos/1851415/pexels-photo-1851415.jpeg

@snap[north-west]
### @color[#27003D](Kommentarer)
@snapend

Note:
- Bilde: kommentar-postits
- Berømt sitat: 
- lies, damn lies.. and statistics. 
- Vel, vi kan legge på kommentarer på denne listen også. Kommentarer er ofte mer til hinder enn hjelp. 



---

@snap[north]
### Kommentarer forteller hvorfor
@snapend

```
if (number != undefined) { // Kan ikke bruke truthy/falsey her fordi
// tallet 0 er falsey
  doStuff();
}
```

Note:
- trenger du kommentarer til å forklare hva koden gjør er det på tide å
  refaktorere
- kode forteller hva som skjer
- kommentarer forklarer hvorfor en løsning er valgt
- kommentarer som kommenterer innlysende ting (repetisjon av funksjonsnavn feks,
  forvirrer og gjør utvikleren usikker)



---

@snap[north]
### Kommentarer må holdes i sync
@snapend

```
public double estimateNumberOfPizzasToOrder(int numberOfCoders) {
  // codersPerPizza is set to 2.0 because of hungry coders
  double codersPerPizza = 3.0;
  return (numberOfCoders / codersPerPizza) * 0.8;
}

```

Note:
- denne linjen tør ikke jeg endre. Er det kommentaren eller koden som er riktig?
  Hvorfor ble det satt opp slik? 
- kommentarer impliserer at noe er viktig, og da blir du usikker når det ikke
  stemmer med virkeligheten



---

@snap[north]
### Reduser usikkerhet
@snapend

Note:
- Oppsummert: reduser kognitiv belastning så mye som mulig for den som skal lese
  koden
- navngiving er essensielt og tar mye tid
- kommentarer brukes kun når det er absolutt nødvendig
- funksjoner og klasser: små, har kun et ansvarsområde
- hold det så enkelt som mulig



---

### Refaktorering


@quote[A change made to the internal structure of software to make it easier to understand and cheaper to modify without changing its observable behavior](Martin Fowler)

Note:
- også kalt refaktorisering
- endre kode uten å endre resultat av koden


---

### Lesbarhet er essensielt

Note:
- økt lesbarhet gir økt vedlikeholdbarhet
- kode du har skrevet for 6 mnd siden kunne like gjerne vært skrevet av noen
  andre
- 60 - 80% av tiden vår brukes på å lese kode
- når trenger vi å refaktorere? 
- hvilke refaktoreringsmetoder finnes?


---

### Vanlige refaktoreringsmetoder

- Forbedre navngiving
- Flytt på kode
- Dupliser kode midlertidig: minimum red refactoring

Note: 
- Gjør koden selvdokumenterende
- navngiving kan være vanskelig
- variabler, funksjoner, klasser. 
- extract variable, function, class
- kode som står for seg selv, uavhengig av andre ting, eget konsept, kan få egen
  funksjon/klasse
- bruker du samme variabel mange steder uten å si det? Lag en variabel så det er
  lett å se hvor den kommer fra og alle steder den er brukt
- indikerer at disse tingene henger sammen, og at de ikke henger sammen med
  resten av koden
- både parametre og oppførsel som er egen enhet? Da er det gjerne en klasse
- Prøv ut, bli vant til å eksperimentere med kodeendringer. Commit før du
  begynner, så er det lett å rulle tilbake. Ha godt med tester, så er du trygg
  på at du ikke ødelegger noe
- motsatt av extract: inline: har du en funksjon med en linje som bare finnes et
  sted? Kanskje det ikke trenger å være en funksjon. Lesbarhet senkes hvis det
  finnes for mye av dette
- move data (flytt variabler til annen klasse, utløses typisk av feature envy)
- flytt funksjon til annen klasse hvis den passer bedre der


---

### Dagens oppgave

https://github.com/kantega/intro-refaktorering

Note: 
- en testklasse, en implementasjonsklasse
- ca 50 linjer produksjonskode
- full testdekning: prøv å ha grønne tester mesteparten av tiden
- kvaliteten er ikke bra nok, den må forbedres
- koden hjelper puben med å gjøre bestillinger og holde styr på hva som er i
  menyen: Det må bli lettere å legge til nye eller andre drikkevarer i menyen

