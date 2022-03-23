/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.script.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRewriteTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.modelio.script.plugin.Script;

/**
 * Manages drag & drop on a TextViewer.
 * <p>
 * Most code copied from AbstractTextEditor.
 */
@objid ("00875276-663d-105c-84ef-001ec947cd2a")
class TextViewerDragDropManager {
    @objid ("00723724-572b-1064-a2b8-001ec947cd2a")
    protected Object fTextDragAndDropToken = null;

    @objid ("007265f0-572b-1064-a2b8-001ec947cd2a")
    protected ScriptView viewPart;

    @objid ("005ae1b4-f1bd-106a-bf4f-001ec947cd2a")
    protected final TextTransfer textTransfer = TextTransfer.getInstance();

    @objid ("005af1fe-f1bd-106a-bf4f-001ec947cd2a")
    protected final FileTransfer fileTransfer = FileTransfer.getInstance();

    @objid ("005b0216-f1bd-106a-bf4f-001ec947cd2a")
    protected StyledText textWidget;

    @objid ("0060b5f8-0b95-109d-896e-001e4fea2d8b")
    protected TextViewer textViewer;

    /**
     * Installs text drag and drop on the given TextViewer.
     * @param aViewPart
     * @param aTextViewer
     */
    @objid ("0087e420-663d-105c-84ef-001ec947cd2a")
    public  TextViewerDragDropManager(ScriptView aViewPart, TextViewer aTextViewer) {
        this.viewPart = aViewPart;
        this.textViewer = aTextViewer;
        this.textWidget = aTextViewer.getTextWidget();
        
        // Install drag source
        final DragSource source = new DragSource(this.textWidget, DND.DROP_COPY | DND.DROP_MOVE);
        source.setTransfer(new Transfer[] { this.textTransfer });
        source.addDragListener(new DragSourceAdapter() {
            private String fSelectedText;
            private Point fSelection;
            private final ISelectionProvider selectionProvider = TextViewerDragDropManager.this.textViewer.getSelectionProvider();
        
            @Override
            public void dragStart(DragSourceEvent event) {
                TextViewerDragDropManager.this.fTextDragAndDropToken = null;
                try {
                    this.fSelection = TextViewerDragDropManager.this.textWidget.getSelection();
                    int offset = TextViewerDragDropManager.this.textWidget.getOffsetAtLocation(new Point(event.x, event.y));
                    final Point p = TextViewerDragDropManager.this.textWidget.getLocationAtOffset(offset);
                    if (p.x > event.x) {
                        offset--;
                    }
                    event.doit = offset >= this.fSelection.x && offset < this.fSelection.y;
        
                    final ISelection selection = this.selectionProvider.getSelection();
                    if (selection instanceof ITextSelection) {
                        this.fSelectedText = ((ITextSelection) selection).getText();
                    } else {
                        this.fSelectedText = TextViewerDragDropManager.this.textWidget.getSelectionText();
                    }
                } catch (IllegalArgumentException ex) {
                    event.doit = false;
                }
            }
        
            @Override
            public void dragSetData(DragSourceEvent event) {
                event.data = this.fSelectedText;
                TextViewerDragDropManager.this.fTextDragAndDropToken = this; // Can
                // be
                // any
                // non-null
                // object
            }
        
            @Override
            public void dragFinished(DragSourceEvent event) {
                try {
                    if (event.detail == DND.DROP_MOVE) {
                        final Point newSelection = TextViewerDragDropManager.this.textWidget.getSelection();
                        final int length = this.fSelection.y - this.fSelection.x;
                        int delta = 0;
                        if (newSelection.x < this.fSelection.x) {
                            delta = length;
                        }
                        TextViewerDragDropManager.this.textWidget.replaceTextRange(this.fSelection.x + delta, length, ""); //$NON-NLS-1$
        
                        if (TextViewerDragDropManager.this.fTextDragAndDropToken == null) {
                            // Move in same editor - end compound change
                            final IRewriteTarget target = TextViewerDragDropManager.this.textViewer.getRewriteTarget();
                            if (target != null) {
                                target.endCompoundChange();
                            }
                        }
        
                    }
                } finally {
                    TextViewerDragDropManager.this.fTextDragAndDropToken = null;
                }
            }
        });
        
        // Install drag target
        DropTargetListener dropTargetListener = new DropTargetAdapter() {
        
            private Point fSelection;
        
            /**
             * Returns true if only text files are dragged.
             *
             * @param event
             *            the drag event
             * @return true if only text files are dragged, false in the other
             *         case.
             */
            private boolean acceptDropFiles(DropTargetEvent event) {
                final String[] files = (String[]) event.data;
                for (String filePath : files) {
                    if (!isTextFile(filePath)) {
                        return false;
                    }
        
                }
        
                return true;
            }
        
            @Override
            public void dragEnter(DropTargetEvent event) {
                TextViewerDragDropManager.this.fTextDragAndDropToken = null;
                this.fSelection = TextViewerDragDropManager.this.textWidget.getSelection();
        
                // will accept text but prefer to have files dropped
                for (int i = 0; i < event.dataTypes.length; i++) {
                    if (TextViewerDragDropManager.this.fileTransfer.isSupportedType(event.dataTypes[i])) {
                        event.currentDataType = event.dataTypes[i];
                        // files should only be copied
                        // and only text files are accepted
                        if (!acceptDropFiles(event)) {
                            event.detail = DND.DROP_NONE;
                        } else if (event.detail == DND.DROP_DEFAULT) {
                            event.detail = DND.DROP_COPY;
                        } else if (event.detail != DND.DROP_COPY) {
                            event.detail = DND.DROP_NONE;
                        }
                        break;
                    }
                }
        
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_MOVE) != 0) {
                        event.detail = DND.DROP_MOVE;
                    } else if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
        
            }
        
            @Override
            public void dragOperationChanged(DropTargetEvent event) {
                if (event.detail == DND.DROP_DEFAULT) {
                    if ((event.operations & DND.DROP_MOVE) != 0) {
                        event.detail = DND.DROP_MOVE;
                    } else if ((event.operations & DND.DROP_COPY) != 0) {
                        event.detail = DND.DROP_COPY;
                    } else {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }
        
            @Override
            public void dragOver(DropTargetEvent event) {
                event.feedback |= DND.FEEDBACK_SCROLL;
            }
        
            @Override
            public void drop(DropTargetEvent event) {
                if (TextViewerDragDropManager.this.textTransfer.isSupportedType(event.currentDataType)) {
                    dropText(event);
                }
                if (TextViewerDragDropManager.this.fileTransfer.isSupportedType(event.currentDataType)) {
                    dropFiles(event);
                }
            }
        
            /**
             * Tests whether the given file contains ASCII text. This is done by
             * reading the first 512 bytes and ensuring that they are all
             * ASCII characters of the kind you'd expect to find in source
             * files.
             *
             */
            private boolean isTextFile(String file) {
                boolean isTextFile = false;
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    final byte[] bytes = new byte[512];
                    final int byteCount = fileInputStream.read(bytes);
                    fileInputStream.close();
        
                    int zeroByteCount = 0;
                    for (int i = 0; i < byteCount; ++i) {
                        if (bytes[i] == 0) {
                            ++zeroByteCount;
                        }
                    }
        
                    // Consider file as text file if there is no 00 byte
                    isTextFile = (byteCount == -1 || zeroByteCount == 0);
                } catch (IOException ex) {
                    // Not a text file
                }
                return isTextFile;
            }
        
            public void dropFiles(DropTargetEvent event) {
                final String[] files = (String[]) event.data;
                final StringBuilder errors = new StringBuilder();
                final StringBuilder text = new StringBuilder();
                for (String filePath : files) {
                    // Load each file in the console
                    try {
                        if (isTextFile(filePath)) {
                            text.append(readFileContents(filePath));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
        
                        errors.append(e.getLocalizedMessage());
                        errors.append("\n");
                    }
                }
        
                if (errors.length() > 0) {
                    MessageDialog.openError(TextViewerDragDropManager.this.textWidget.getShell(), "Error", errors.toString());
                }
        
                dropText(text.toString());
        
            }
        
            private String readFileContents(String file) throws FileNotFoundException, IOException {
                try (Reader reader = new FileReader(file);
                        final Reader in = new BufferedReader(reader)) {
                    final StringBuffer buffer = new StringBuffer(512);
                    final char[] readBuffer = new char[512];
        
                    int n = in.read(readBuffer);
                    while (n > 0) {
                        buffer.append(readBuffer, 0, n);
                        n = in.read(readBuffer);
                    }
        
                    in.close();
        
                    return (buffer.toString());
                }
            }
        
            public void dropText(DropTargetEvent event) {
                try {
                    if (TextViewerDragDropManager.this.fTextDragAndDropToken != null && event.detail == DND.DROP_MOVE) {
                        // Move in same editor
                        final int caretOffset = TextViewerDragDropManager.this.textWidget.getCaretOffset();
                        if (this.fSelection.x <= caretOffset && caretOffset <= this.fSelection.y) {
                            event.detail = DND.DROP_NONE;
                            return;
                        }
        
                        // Start compound change
                        final IRewriteTarget target = TextViewerDragDropManager.this.textViewer.getRewriteTarget();
                        if (target != null) {
                            target.beginCompoundChange();
                        }
                    }
        
                    final String text = (String) event.data;
                    dropText(text);
        
                } finally {
                    TextViewerDragDropManager.this.fTextDragAndDropToken = null;
                }
            }
        
            private void dropText(String text) {
                final Point newSelection = TextViewerDragDropManager.this.textWidget.getSelection();
        
                final int modelOffset = TextViewerDragDropManager.this.textViewer.widgetOffset2ModelOffset(newSelection.x);
                try {
                    TextViewerDragDropManager.this.textViewer.getDocument().replace(modelOffset, 0, text);
                } catch (BadLocationException e) {
                    Script.LOG.error(e);
                }
        
                TextViewerDragDropManager.this.textWidget.setSelectionRange(newSelection.x, text.length());
            }
        };
        
        final int dropOperations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
        final Transfer[] dropableTypes = new Transfer[] { this.fileTransfer, this.textTransfer };
        
        // note: Seems to be always null in our case.
        // final IDragAndDropService dndService = (IDragAndDropService)
        // this.viewPart.getSite().getService(IDragAndDropService.class);
        //
        // if (dndService != null) {
        // dndService.addMergedDropTarget(this.textWidget, dropOperations,
        // dropableTypes, dropTargetListener);
        //
        // // Call drag drop dispose when disposing the widget.
        // this.textWidget.addDisposeListener(new DisposeListener() {
        // @Override
        // public void widgetDisposed(DisposeEvent e) {
        // disposeMergedDropTarget();
        // }
        // });
        // } else {
        // final DropTarget target = new DropTarget(this.textWidget,
        // dropOperations);
        // target.setTransfer(dropableTypes);
        // target.addDropListener(dropTargetListener);
        // }
        
    }

    /**
     * Uninstalls text drag and drop from the source viewer.
     */
    @objid ("00881940-663d-105c-84ef-001ec947cd2a")
    public void disposeMergedDropTarget() {
        // if (viewer == null || !fIsTextDragAndDropInstalled)
        // return;
        
        // final IDragAndDropService dndService = (IDragAndDropService)
        // this.viewPart.getSite().getService(IDragAndDropService.class);
        // if (dndService == null) {
        // return;
        // }
        //
        // dndService.removeMergedDropTarget(this.textWidget);
        //
        // DragSource dragSource = (DragSource)
        // this.textWidget.getData(DND.DRAG_SOURCE_KEY);
        // if (dragSource != null) {
        // dragSource.dispose();
        // this.textWidget.setData(DND.DRAG_SOURCE_KEY, null);
        // }
        
        // fIsTextDragAndDropInstalled= false;
        
    }

}
