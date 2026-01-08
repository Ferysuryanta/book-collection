
package com.book.collection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    @NotBlank
    public String title;
    @NotBlank
    public String author;
    @NotBlank
    @Pattern(regexp="\\d+", message="ISBN must be numeric")
    public String isbn;
    public Integer publicationYear;
    public String genre;
    public String description;
}
