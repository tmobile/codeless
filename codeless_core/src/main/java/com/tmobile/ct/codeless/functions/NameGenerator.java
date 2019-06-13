package com.tmobile.ct.codeless.functions;

import java.security.SecureRandom;
import java.util.Random;

public class NameGenerator {

    private static final String firstNames[] = {"Christy","Thebault","Zackariah","Tildi","Iris","Oralle","Alexei","Lani","Alina","Tamqrah","Abramo","Fredrika","Devlen","Ara","Gawen","Donal","Babara","Annissa","Laurel","Marijn","Brooks","Mala","Matias","Winslow","Kath","Guenna","Orly","Pepito","Dolly","Lindsay","Tobey","Kim","Lynett","Gabey","Elmira","Torie","Nanice","Sherrie","Libbi","Amity","Frasquito","Salome","Lula","Wolfgang","Edna","Cosimo","Dominique","Llywellyn","Johan","Umeko","Gwenny","Bianca","Tamas","Morrie","Jody","Vincenty","Brittney","Kym","Thomas","Laughton","Marv","Naoma","Flinn","Sigismund","Constantina","Ambrosius","Marty","Leontine","Constantina","Marna","Ollie","Shannon","Renault","Patrick","Gardener","Debby","Weidar","Ansel","Niccolo","Vittorio","Evangeline","Laurens","Carlin","Anderea","Jacqueline","Miranda","Titus","Alli","Faber","Lyssa","Clemente","Hetti","Ervin","Norton","Emyle","Marcille","Minetta","Fanchette","Brennen","Bartel"};
    private static final String lastNames[] = {"Dumberell","Hastie","Belchamber","Goeff","O'Monahan","Wiley","Garbutt","Huitson","Andersen","Caldera","McNeish","Bumphrey","Nattrass","Vankov","Hagwood","O'Farris","Basil","Terrington","Copcote","McRinn","Birchill","Gundry","Grennan","Crickmoor","Rosenfrucht","Confort","Skedge","Hayball","Bycraft","Pavlovsky","Beynon","Free","Ducker","Barkworth","Rodder","Lydden","Conyer","Lanigan","Ridgwell","Gabbitas","Hamblyn","De'Vere - Hunt","Karpychev","Hansod","Wilce","Wintringham","Capps","Oak","Brinkler","Celli","Stopper","Skehens","Dowyer","Paulsson","Smewin","Clementi","Hamstead","Hellier","Pfeffle","Lamble","Illsley","Dewar","MacEnelly","Landman","Bowhey","Roles","Noseworthy","Simonsen","Capel","Gosart","Hrycek","Seacombe","Godbolt","Jachimczak","Dermot","Sconce","Stockney","Tother","Eames","Barta","Goward","Yea","De Launde","Spieck","Puxley","Gretton","Amar","Badrick","Tuxsell","Halt","Robbings","Blakebrough","Leghorn","Camoletto","Gammett","Dopson","Scargle","Feast","Kmietsch","Fiddyment"};
    private static final String fullNames[] = {"Mitchael Marcq","Bowie Di Matteo","Giulio Bottomer","Camella Glanz","Caspar Bullar","Elsa Perceval","Brunhilda Padbury","Aline Plum","Alick Bygate","Lauralee Beevors","Hillery Lidgett","Emilio Custed","Clarita Greder","Nari McKeady","Jacquie Flattman","Chico Kield","Ariel Cardenas","Latisha De Bruijne","Ilka Nuton","Vernor Klampt","Jock Aingel","Anabelle Antonognoli","Henrie Inmett","Sutherlan Woodier","Peggi Bartke","Sigvard Mitham","Aloin Fishleigh","Tobiah Massen","Hazel Avramov","Rupert Hadlington","Mannie Duham","Radcliffe Minett","Valeda McNiff","Westley Bowdon","Sada Gudger","Stirling Ambrosch","Bengt Davison","Adolphe Oldmeadow","Kenn Spurryer","Bard Lockley","Jen Burris","Kile Cowins","Cazzie Lingard","Saul Grzelczak","Maddie McGinlay","Reese Fumagall","Bee Mitchley","Helenelizabeth Jumeau","Eugenia Espino","Eduard Gunther","Tallie McIlmorow","Kylynn Simonson","Jasper Heinzler","Brier Tillett","Gisele Bloxsom","Alida Bengall","Dar Ferie","Tish Foottit","Devlen Cyphus","Mollie Wabb","Charyl MacCallester","Morgana Paydon","Gianna Mitkcov","Haleigh Shuttleworth","Valli Gailor","Sylas Pavyer","Karoline MacCarrick","Glynnis Rollo","Keeley Silby","Wilhelm O'Suaird","Helaine Zamora","Selena Realph","Martynne Tummond","Chic Tyrone","Fredrika Cypler","Steve Tine","Cherry Camerana","Dalton Grogona","Shea Danilchev","Sharai Huikerby","Ruthi Lanon","Ignacio Senechault","Heywood Wysome","Ingrid Seville","Quincy Baskwell","Brant Laboune","Lynn Carlens","Merralee Carney","Elvira Currum","Grissel Feavearyear","Yetty Chritchley","Gracia Vanns","Leigha Dusey","Ermin Gramer","Domini Johnston","Eden Dakhov","Mitzi Marjanski","Hagan Smoughton","Dill Mafham","Sherie Luton"};

    public String randomFullName(){
        Random random = new SecureRandom();
        return fullNames[random.nextInt(fullNames.length)];
    }

    public String randomFirstName(){
        Random random = new SecureRandom();
        return firstNames[random.nextInt(firstNames.length)];
    }

    public String randomLastName(){
        Random random = new SecureRandom();
        return lastNames[random.nextInt(lastNames.length)];
    }

    public String generate(String param){
        switch (param.toUpperCase().trim()){
            case "FIRSTNAME":
                return randomFirstName();
            case "LASTNAME":
                return randomLastName();
            default:
                return randomFullName();
        }
    }

}
