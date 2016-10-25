package seedu.whatnow.testutil;

import java.util.Objects;

import seedu.whatnow.commons.exceptions.IllegalValueException;
import seedu.whatnow.model.tag.UniqueTagList;
import seedu.whatnow.model.task.*;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private String date;
    private String startDate;
    private String endDate;
    private String time;
    private String startTime;
    private String endTime;
    private UniqueTagList tags;
    private String status;
    private String taskType; //todo or schedule

    public TestTask() throws IllegalValueException {
        setDate(null);
        setStartDate(null);
        setEndDate(null);
        setTime(null);
        setStartTime(null);
        setEndTime(null);
        setTaskType(null);
        setStatus("incomplete");
        tags = new UniqueTagList();
    }
    
    @Override
    public Name getName() {
        return this.name;
    }
    
    public void setName(Name name) {
        this.name = name;
    }    
    
    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }
    
    @Override
    public String getStatus() {
        return this.status;
    }

    public String setStatus(String status) {
        return this.status = status;
    }
    
    @Override
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    
    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setStartDate(String date) {
        this.startDate = date;
    }
    
    public void setEndDate(String date) {
        this.endDate = date;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public void setStartTime(String time) {
        this.startTime = time;
    }
    
    public void setEndTime(String time) {
        this.endTime = time;
    }
    
    @Override
    public String toString() {
        return getAsText();
    }
    
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, status, tags, taskType, startDate, endDate, startTime, endTime);
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add \"" + this.getName().fullName + "\" ");
        if (this.getStartDate() != null && !this.getStartDate().equals("")) {
            sb.append("on" + " " + this.getStartDate());
        } else if (this.getDate() != null && !this.getDate().equals("")) {
            sb.append("on" + " " + this.getDate());
        }
        this.getTags().getInternalList().stream().forEach(s -> sb.append(" t/" + s.tagName + " "));
        return sb.toString();
    }
}
