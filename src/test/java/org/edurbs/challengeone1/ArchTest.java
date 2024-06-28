package org.edurbs.challengeone1;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    private final JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("org.edurbs.challengeone1..");

    @Test
    void domainShouldNotDependeOnOutside(){
        noClasses()
                .that()
                .resideInAPackage("org.edurbs.challengeone1.application.domain")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(
                        "org.edurbs.challengeone1.application.domain..",
                        "jakarta..",
                        "java..")
                .check(importedClasses);
    }

    @Test
    void applicationShouldNotDependeOnOutside(){
        noClasses()
                .that()
                .resideInAnyPackage("org.edurbs.challengeone1.application.gateways",
                        "org.edurbs.challengeone1.application.usecases")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(
                        "org.edurbs.challengeone1.application..",
                        "jakarta..",
                        "java..")
                .check(importedClasses);
    }
}
