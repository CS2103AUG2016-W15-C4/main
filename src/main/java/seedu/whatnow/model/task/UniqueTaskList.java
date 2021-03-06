//@@author A0139772U
package seedu.whatnow.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.whatnow.commons.exceptions.DuplicateDataException;
import seedu.whatnow.commons.util.CollectionUtil;

import java.util.*;

/**
 * A list of tasks that enforces uniqueness between its elements and does not
 * allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {
    
    private ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Signals that an operation would have violated the 'no duplicates'
     * property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would
     * fail because there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {
    }

    public static class NoPrevCommandException extends Exception {
        public NoPrevCommandException() {
            super("No previous Command was found");
        }
    }

    // private Stack<Task[]> reqStack = new Stack<>();

    // private Task[] array = new Task[];
    /**
     * Constructs empty TaskList.
     */
    public UniqueTaskList() {
    }

    /**
     * Returns true if the list contains an equivalent task as the given
     * argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException
     *             if the task to add is a duplicate of an existing task in the
     *             list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }

        internalList.add(toAdd);
    }

    public void addSpecific(Task toAddSpecific, int idx) throws DuplicateTaskException {
        assert toAddSpecific != null;
        if (contains(toAddSpecific)) {
            throw new DuplicateTaskException();
        }

        internalList.add(idx, toAddSpecific);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException
     *             if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    // @@author A0126240W
    /**
     * Updates the equivalent task from the list.
     *
     * @throws TaskNotFoundException
     *             if no such task could be found in the list.
     */
    public boolean update(ReadOnlyTask old, Task toUpdate) throws TaskNotFoundException, DuplicateTaskException {
        assert old != null;
        final boolean taskFoundAndUpdated = internalList.contains(old);
        if (!taskFoundAndUpdated) {
            throw new TaskNotFoundException();
        }
        if (internalList.contains(toUpdate)) {
            throw new DuplicateTaskException();
        }
        internalList.set(internalList.indexOf(old), toUpdate);
        return taskFoundAndUpdated;
    }

    // @@author A0139772U
    /**
     * Mark the equivalent task from the list as completed.
     */
    public boolean mark(ReadOnlyTask target) throws TaskNotFoundException {
        assert target != null;
        final boolean taskFoundAndMarked = internalList.contains(target);
        if (!taskFoundAndMarked) {
            throw new TaskNotFoundException();
        }
        internalList.get(internalList.indexOf(target)).setStatus("completed");
        internalList.set(internalList.indexOf(target), internalList.get(internalList.indexOf(target)));
        return taskFoundAndMarked;
    }

    // @@author A0141021H
    /**
     * 
     * Mark the equivalent task from the list as uncompleted.
     */
    public boolean unmark(ReadOnlyTask target) throws TaskNotFoundException {
        assert target != null;
        final boolean taskFoundAndMarked = internalList.contains(target);
        if (!taskFoundAndMarked) {
            throw new TaskNotFoundException();
        }
        internalList.get(internalList.indexOf(target)).setStatus("incomplete");
        internalList.set(internalList.indexOf(target), internalList.get(internalList.indexOf(target)));
        return taskFoundAndMarked;
    }

    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                        && this.internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
