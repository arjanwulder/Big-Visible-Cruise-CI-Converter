package nl.mirabeau.ci.rss.feed.common;

import junit.framework.Assert;
import org.junit.Test;

public class ValidateTest {

    @Test(expected = IllegalArgumentException.class)
    public void mustThrownExceptionWhenArgumentIsNull() {
        // Arrange

        // Act
        Validate.notNull(null);

        // Assert
    }

    @Test
    public void mustDoNothingWhenArgumentIsNotNull() {
        // Arrange

        // Act
        Validate.notNull("");

        // Assert
    }

    @Test
    public void mustThrowExceptionWithMessageWhenArgumentIsNull() {
        // Arrange
        String exceptionMessage = "Object is required";
        String exceptionMessageFromException = "";

        // Act
        try {
            Validate.notNull(null, exceptionMessage);
        } catch (IllegalArgumentException e) {
            exceptionMessageFromException = e.getMessage();
        }

        // Assert
        Assert.assertEquals(exceptionMessage, exceptionMessageFromException);
    }

    @Test
    public void mustDoNothingWhenArgumentWithMessageIsNotNull() {
        // Arrange
        String exceptionMessage = "Object is required";
        String exceptionMessageFromException = "";

        // Act
        try {
            Validate.notNull("", exceptionMessage);
        } catch (IllegalArgumentException e) {
            exceptionMessageFromException = e.getMessage();
        }

        // Assert
        Assert.assertEquals("", exceptionMessageFromException);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowExceptionWhenArgumentIsNull() {
        // Arrange

        // Act
        Validate.hasText(null);

        // Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowExceptionWhenArgumentIsEmpty() {
        // Arrange

        // Act
        Validate.hasText("");

        // Assert
    }

    @Test
    public void mustDoNothingWhenArgumentHasTest() {
        // Arrange

        // Act
        Validate.hasText("Has text");

        // Assert
    }

    @Test
    public void mustThrowExceptionWithMessageWhenStringArgumentIsNull() {
        // Arrange
        String exceptionMessage = "Object is required";
        String exceptionMessageFromException = "";

        // Act
        try {
            Validate.hasText(null, exceptionMessage);
        } catch (IllegalArgumentException e) {
            exceptionMessageFromException = e.getMessage();
        }

        // Assert
        Assert.assertEquals(exceptionMessage, exceptionMessageFromException);
    }

    @Test
    public void mustThrowExceptionWithMessageWhenStringArgumentIsEmpty() {
        // Arrange
        String exceptionMessage = "Object is required";
        String exceptionMessageFromException = "";

        // Act
        try {
            Validate.hasText("", exceptionMessage);
        } catch (IllegalArgumentException e) {
            exceptionMessageFromException = e.getMessage();
        }

        // Assert
        Assert.assertEquals(exceptionMessage, exceptionMessageFromException);
    }

    @Test
    public void mustDoNothingWhenStringArgumentWithMessageHasText() {
        // Arrange
        String exceptionMessage = "Object is required";
        String exceptionMessageFromException = "";

        // Act
        try {
            Validate.hasText("Has text", exceptionMessage);
        } catch (IllegalArgumentException e) {
            exceptionMessageFromException = e.getMessage();
        }

        // Assert
        Assert.assertEquals("", exceptionMessageFromException);
    }

}
