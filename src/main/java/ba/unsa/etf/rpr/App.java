package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.InstruktorManager;
import ba.unsa.etf.rpr.business.PredmetManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.domain.Instruktor;
import ba.unsa.etf.rpr.domain.Predmet;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class App {
    private static final Option addInstruktor = new Option("addI", "add-instruktor", false, "Dodavanje novog instruktora u bazu");
    private static final Option addPredmet = new Option("addP", "add-predmet", false, "Dodavanje novog predmeta u bazu");
    private static final Option getInstruktori = new Option("getI", "get-instruktori", false, "Svi instruktori iz baze");
    private static final Option getPredmeti = new Option("getP", "get-predmeti", false, "Svi predmeti iz baze");
    private static final Option deleteInstruktor = new Option("deleteI", "delete-instruktor", false, "Brisanje instruktora iz baze");
    private static final Option deletePredmet = new Option("deleteP", "delete-predmet", false, "Brisanje predmeta iz baze");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr_projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addInstruktor);
        options.addOption(addPredmet);
        options.addOption(getInstruktori);
        options.addOption(getPredmeti);
        return options;
    }

    public static void main(String[] args) throws ParseException, SQLException {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        InstruktorManager ins = new InstruktorManager();
        PredmetManager predmet = new PredmetManager();

        if (cl.hasOption(getInstruktori.getOpt()) || cl.hasOption(getInstruktori.getLongOpt())) {
            List<Instruktor> listaInstruktora = ins.getAll();
            if (listaInstruktora == null)
                System.out.println("Nema dostupnih instruktora");
            else
                for (Instruktor i : listaInstruktora)
                    System.out.println(i);
        } else if (cl.hasOption(getPredmeti.getOpt()) || cl.hasOption(getPredmeti.getLongOpt())) {
            List<Predmet> listaPredmeta = predmet.getAll();
            if (listaPredmeta == null)
                System.out.println("Nema dostupnih predmeta");
            else
                for (Predmet p : listaPredmeta)
                    System.out.println(p);
        } else if (cl.hasOption(deleteInstruktor.getOpt()) || cl.hasOption(deleteInstruktor.getLongOpt())) {
            try {
                int id = Integer.parseInt(cl.getArgList().get(0));
                ins.getById(id); //baca error ako se ne nadje
                ins.delete(id);
                System.out.println("Instruktor izbrisan");
            } catch (Exception e) {
                if (e.getMessage().contains("not found"))
                    System.out.println("Instruktor ne postoji");
                else
                    System.out.println(e.getMessage());
            }
        } else if (cl.hasOption(deletePredmet.getOpt()) || cl.hasOption(deletePredmet.getLongOpt())) {
            try {
                int id = Integer.parseInt(cl.getArgList().get(0));
                predmet.getById(id);
                predmet.delete(id);
                System.out.println("Predmet izbrisan");
            } catch (Exception e) {
                if (e.getMessage().contains("not found"))
                    System.out.println("Predmet ne postoji");
                else
                    System.out.println(e.getMessage());
            }
        } else if (cl.hasOption(addInstruktor.getOpt()) || cl.hasOption(addInstruktor.getLongOpt())) {
            try {
                if (cl.getArgList().size() != 4) {
                    System.out.println("Invalid number of arguments! Expected 5 arguments, recieved " + cl.getArgList().size() + " arguments.");
                    System.out.println("[String nazivInstruktora, String telefonskiBroj, Double cijena, String grad]");
                } else {
                    Instruktor temp = ins.add(new Instruktor(0, cl.getArgList().get(0), cl.getArgList().get(1), Double.parseDouble(cl.getArgList().get(2)), cl.getArgList().get(3)));
                    System.out.println("Dodan instruktor");
                    System.out.println(temp);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (cl.hasOption(addPredmet.getOpt()) || cl.hasOption(addPredmet.getLongOpt())) {
            try {
                if (cl.getArgList().size() != 2) {
                    System.out.println("Invalid number of arguments! Expected 2 arguments, recieved " + cl.getArgList().size() + " arguments.");
                    System.out.println("[String naziv, String nivo]");
                } else {
                    Predmet temp = predmet.add(new Predmet(0, cl.getArgList().get(0), cl.getArgList().get(1)));
                    System.out.println("Predmet dodan");
                    System.out.println(temp);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        } else printFormattedOptions(options);
        if (AbstractDao.getConnection() != null)
            AbstractDao.getConnection().close();
    }
}
