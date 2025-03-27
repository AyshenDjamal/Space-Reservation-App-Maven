package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkSpaceServiceTest {
    @BeforeEach
    void setUp(){
        WorkSpaceService.coworkingSpaces.clear();
    }

    @Test
    void testAddSpace_Success() {
        boolean result = WorkSpaceService.addSpace(1, "open", 23, true);

        assertTrue(result, "Space should be added successfully");

    }

    @Test
    void testAddSpace_DuplicateId(){
        WorkSpaceService.addSpace(1, "private", 20, true);
        boolean duplicateResult = WorkSpaceService.addSpace(1,"private", 21, false);

        assertFalse(duplicateResult, "Should return false for duplicate ID");
    }

    @Test
    void testRemoveSpace_Success() {
        WorkSpaceService.addSpace(1, "open", 45, true);

        assertTrue(WorkSpaceService.removeSpace(1), "Space should be removed.");
    }

    @Test
    void testRemoveSpace_Fails_WhenBooked(){
        WorkSpaceService.addSpace(2, "private",23, false);

        assertFalse(WorkSpaceService.removeSpace(2), "Should not remove a booked space.");
    }

    @Test
    void testRemoveSpace_Fails_WhenIdNotExist(){
        assertFalse(WorkSpaceService.removeSpace(5), "Should not remove a space that does not exist.");
    }

}