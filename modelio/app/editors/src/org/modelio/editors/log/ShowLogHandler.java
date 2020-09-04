/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.editors.log;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.editor.EditorType;
import org.modelio.api.modelio.editor.IMDAEditorListener;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.editors.service.EditionManager;
import org.modelio.editors.service.MDATextEditor;
import org.modelio.editors.texteditors.txt.TXTEditor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.utils.log.writers.PluginLogger;

/**
 * <p>
 * Handler for the &quot;Show Log&quot; command. Opens a read only text editor on the modelio.log file in {user.home}/.modelio/3.2.
 * </p>
 */
@objid ("a29e2c00-4307-4bb9-86f2-523e5d0eb9c1")
public class ShowLogHandler {
    @objid ("2592655e-1e15-48c0-bdf1-b74f4b841e7b")
    @Execute
    public void execute() {
        final Path logPath = Paths.get(PluginLogger.getLogFile());
        IMDATextEditor editor = EditionManager.services().openEditor(null, logPath.toFile(), EditorType.TXTEditor, true, EditionManager.DEFAULT_CHARSET_NAME, "Modelio log", null);
        new LogFileWatcher(logPath, editor).start();
    }

    @objid ("a0bbd7c0-587a-441f-babf-3f70061e513e")
    private static final class LogFileWatcher extends Thread {
        @objid ("de79ba12-22b6-4cdf-b111-caaac1061eb9")
        private final Path logPath;

        @objid ("9ec1680f-af5c-43c2-8da8-78f1808eafe8")
        private final AtomicBoolean stop = new AtomicBoolean(false);

        @objid ("bfce7e44-465a-45a5-874f-4bbbcdb7f60f")
        private final IMDATextEditor editor;

        @objid ("792aafca-b1a9-44ef-ab39-a1bca5b31828")
        private final TextViewer textViewer;

        @objid ("bacba304-7a7f-4c18-b964-b4589f7d4bd8")
        public LogFileWatcher(Path logPath, IMDATextEditor editor) {
            this.logPath = logPath;
            this.editor = editor;
            this.textViewer = ((TXTEditor) ((MDATextEditor) editor).getEditor().getObject()).getViewer();
            
            // Position to the end of the editor
            StyledText styledText = this.textViewer.getTextWidget();
            styledText.setTopIndex(styledText.getLineCount() - 1);
            styledText.setCaretOffset(styledText.getText().length() );
            
            editor.setListener(new IMDAEditorListener() {
                @Override
                public void editorClosed(IMDATextEditor mdaEditor) {
                    stopWatcher();
                }
            
                @Override
                public void documentSaved(IMDATextEditor mdaEditor, ModelElement modelElement, File file) {
                    // Nothing to do
                }
            });
        }

        @objid ("6263074d-801e-4fea-bf22-c853756d4636")
        public boolean isStopped() {
            return this.stop.get();
        }

        @objid ("811e805d-9505-43aa-b278-514f04ac1cb6")
        public void stopWatcher() {
            this.stop.set(true);
        }

        /**
         * On file changed the editor contents are updated.
         * Caret position and scroll position are managed:
         * <ul>
         * <li>if the caret was at the end of the text at the moment of the file changed event, the editor is scrolled to display the end of the new contents</li>
         * <li>if the caret was NOT at the end of the text at the moment of the file changed event, the editor is scrolled to its former position</li>
         * </ul>
         */
        @objid ("34c01194-0b14-4e5d-8417-39e4e9f309ee")
        public void onFileChanged() {
            Display.getDefault().asyncExec(() -> {
                try {
                    StyledText styledText = this.textViewer.getTextWidget();
                    if (this.editor != null && styledText != null && !styledText.isDisposed()) {
                        int caretOffset = styledText.getCaretOffset();
                        int topIndex = styledText.getTopIndex();
                        boolean autoScroll = (caretOffset == styledText.getText().length() );
                        this.editor.reload();
                        if (autoScroll) {
                            styledText.setTopIndex(styledText.getLineCount() - 1);
                            styledText.setCaretOffset(styledText.getText().length() );
                        } else {
                            styledText.setTopIndex(topIndex);
                            styledText.setCaretOffset(caretOffset);
                        }
                    } else {
                        // Abort watcher
                        stopWatcher();
                    }
                } catch (RuntimeException e) {
                    // Handle strange NPE when editor is closed in strange way.
            
                    // Abort watcher to avoid infinite loop
                    stopWatcher();
            
                    // Dump directly to stderr instead of log to avoid infinite loop
                    e.printStackTrace();
                }
            });
        }

        @objid ("223e884c-94ed-454a-aefc-08140f4583ab")
        @Override
        public void run() {
            try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
                Path logFileName = this.logPath.getFileName();
                Path path = this.logPath.getParent();
                path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
                while (!isStopped()) {
                    WatchKey key;
                    try {
                        key = watcher.poll(25, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (key == null) {
                        Thread.yield();
                        continue;
                    }
            
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            Thread.yield();
                            continue;
                        } else {
                            if (kind == java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY && logFileName.equals(event.context())) {
                                onFileChanged();
                            }
                        }
                        boolean valid = key.reset();
                        if (!valid) {
                            break;
                        }
                    }
                    Thread.yield();
                }
            } catch (Throwable e) {
                // Ignore error, just end the thread
            }
        }

    }

}
