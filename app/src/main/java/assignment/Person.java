package assignment;

import java.time.LocalDate;

public record Person(String name, Sex sex, LocalDate birthDate) {
}
