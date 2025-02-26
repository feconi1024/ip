package fairy.storage;

import java.io.*;
import java.util.Iterator;

import fairy.task.Task;
import fairy.task.TaskList;
import fairy.ui.Ui;

public class Storage {

    private static final String MESSAGE_RECORD_ADDED = "%d of %d lines added to the list of tasks.";
    private static final String MESSAGE_FAILURES_EXIST =
            " \nFailures may because of incorrect format or corrupted file.";
    private static final String MESSAGE_FILE_NOT_FOUND = "No record found. List starts empty.";
    private static final String MESSAGE_IO_EXCEPTION = "I/O exception: ";

    private static final String MESSAGE_RECORD_SAVED = "Tasks saved.";
    private static final String MESSAGE_ERROR_SAVING = "Error saving file: ";

    private final String directory;
    private final String fileName;

    public Storage(String dir, String filename) {
        this.directory = dir;
        this.fileName = filename;
    }

    public void readFile(TaskList taskList, Ui ui) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int effectiveLines = 0;
            int totalLines = 0;
            while ((line = reader.readLine()) != null) {
                effectiveLines += taskList.addTaskFromRecord(line);
                totalLines += 1;
            }
            reader.close();
            if (effectiveLines != totalLines) {
                ui.showStandardFormat(String.format(MESSAGE_RECORD_ADDED + MESSAGE_FAILURES_EXIST,
                        effectiveLines, totalLines));
            } else {
                ui.showStandardFormat(String.format(MESSAGE_RECORD_ADDED,
                        totalLines, effectiveLines));
            }

        } catch (FileNotFoundException e) {
            ui.showStandardFormat(MESSAGE_FILE_NOT_FOUND);
        } catch (IOException e) {
            ui.showStandardFormat(MESSAGE_IO_EXCEPTION + e.getMessage());
        }
    }

    public void saveFile(TaskList taskList, Ui ui) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Iterator<Task> it = taskList.iterator(); it.hasNext(); ) {
                Task task = it.next();
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
            ui.showStandardFormat(MESSAGE_RECORD_SAVED);
        } catch (IOException e) {
            ui.showStandardFormat(MESSAGE_ERROR_SAVING + e.getMessage());
        }
    }
}
