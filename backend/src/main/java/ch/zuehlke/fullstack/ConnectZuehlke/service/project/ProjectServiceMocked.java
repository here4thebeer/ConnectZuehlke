package ch.zuehlke.fullstack.ConnectZuehlke.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Location;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile({"ci", "default"})
public class ProjectServiceMocked implements ProjectService {

    @Override
    public List<Project> getProjects() {
        Location swisscomLocation = new Location("Förrlibruckstrasse 181", "Zürich", 8005, "Schweiz",  8.511068f, 47.391702f);
        List<String> swisscomSkills = Arrays.asList("Java", "Tyoescript", "Javascript", "AngularJS", "XSLT");
        Project swisscom = new Project("SAM TO", swisscomLocation, "Zühlke Switzerland", "C18180", "Telecom", swisscomSkills, "https://insight.zuehlke.com/api/v1/projects/C18180/picture", "https://insight.zuehlke.com/api/v1/customers/18563/logo", "https://insight.zuehlke.com/projects/C18180", 100, true, "A telecom company offers its customers a web-based customer center for managing customer and subscription data. It is part of a growing system landscape with more than 50 peripheral systems. New, product-specific systems are constantly being integrated via Web services and other interfaces. The competitive situation and the growing number of users make it necessary to implement new functions quickly and with high quality and to bring them to market. The use of Scrum as an agile development method makes this possible.");

        Location sixLocation = new Location("Hardturmstrasse 201", "Zürich", 8005, "Schweiz", 8.509338f, 47.393659f);
        List<String> sixSkills = Arrays.asList("Java", "Javascript", "JPA");
        Project six = new Project("Starlight", sixLocation, "Zühlke Switzerland", "C20098", "Banking & Financial Services", sixSkills, "https://insight.zuehlke.com/api/v1/projects/C20098/picture", "https://insight.zuehlke.com/api/v1/customers/7801/logo", "https://insight.zuehlke.com/projects/c20098", 50, false, "SIX provides customers worldwide with business-critical financial data. A central system in the delivery chain is based on outdated technology and there is a risk that the system will no longer be able to handle the load due to the rapidly growing volume of data. SIX lacks the know-how and experience to renew such a large and complex system, which places the highest demands on data quality and does not allow any interruptions. Zühlke brings the outdated central system for data delivery with the financial service provider into a high-performance application. Zühlke transfers the legacy code to modern code and helps to transfer the know-how to SIX with the experts on site. Zühlke also assists SIX in the transition to agile development and the introduction of the DevOps approach. The implementation in short stages allows precise project control with partial steps that reduce complexity and allow one to be implemented.");

        Location sbbLocation = new Location("Lindenhofstrasse 1", "Worblaufen", 3048, "Schweiz",  7.459547f, 46.980497f);
        List<String> sbbSkills = Arrays.asList("Java", "Jenkins");
        Project sbb = new Project("Sanierung Finanzen", sbbLocation, "Zühlke Switzerland", "C22669", "Transportation & Logistics", sbbSkills, "https://insight.zuehlke.com/api/v1/projects/C22669/picture", "https://insight.zuehlke.com/api/v1/customers/18089/logo", "https://insight.zuehlke.com/projects/C22669", 10, true, "The major project \"Renovation of SBB distribution systems\" SVS focuses on the technical modernisation of the IT landscape and the connection of SBB systems to the new pricing platform for public transport. In this context, customer/user experiences and sales processes are constantly being optimised, and customer added value is created through new or improved offers. This project comprises one of approx. 10 Scrum teams in the overall programme.");

        Location rhbLocation = new Location("Bahnhofstrasse 25", "Chur", 7001, "Schweiz", 9.532560f, 46.851926f);
        List<String> rhbSkills = Arrays.asList("Java");
        Project rhb = new Project("Räthische Bahn", rhbLocation, "Zühlke Switzerland", "C16454", "Transportation & Logistics", rhbSkills, "https://insight.zuehlke.com/api/v1/projects/C16454/picture", "https://insight.zuehlke.com/api/v1/customers/343/logo", "https://insight.zuehlke.com/projects/C16454", 25, false, "Eine schweizerische Privatbahn lässt eine Applikation zur Lokalisierung ihres Rollmaterials entwickeln. Lesegeräte detektieren mittels RFID-Technologie die Fahrzeuge und übermitteln die Daten über einen Webservice der Applikation. Die Lokalisierungsdaten werden in einer Datenbank persistiert. Mitarbeiter können über eine Webapplikation Stammdaten mutieren und Statusinformationen der Fahrzeuge sowie der Lesegeräte abrufen. Neben der Visualisierung der Lokalisierungsdaten zeigt die Lösung die zurückgelegte Strecke jedes Fahrzeuges. Die Daten können gefiltert und in Excel exportiert werden. Ein Alarmierungsmechanismus sendet Mails, falls Fahrzeuge in einem definierten Zeitraum nicht detektiert wurden oder Lesegeräte nicht mehr verfügbar sind.");

        Location eonLocation = new Location("Westminster", "London", 1000, "England", -0.141911f, 51.501544f);
        List<String> eonSkills = Arrays.asList("Java");
        Project eon = new Project("E-ON Self Service Portal", eonLocation, "Zühlke UK", "C23172", "Energy & Water", eonSkills, "https://insight.zuehlke.com/api/v1/projects/C23172/picture", "https://insight.zuehlke.com/api/v1/customers/19942/logo", "https://insight.zuehlke.com/projects/C23172", 5, false, "This is the description");

        return Arrays.asList(swisscom, six, sbb, rhb, eon);
    }

}