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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.actions.ActionFactory;
import org.modelio.script.IInputView;

/**
 * Script view input zone.
 * <p>
 * The user can type here script code to execute. He has access to cut, copy, paste, undo and redo commands.<br>
 * The input view is cleared when running the entered script unless the debug mode is activated.
 */
@objid ("0080f2dc-663d-105c-84ef-001ec947cd2a")
class InputView extends SourceViewer implements IInputView {
    /**
     * Contextual menu ID
     */
    @objid ("00810ee8-663d-105c-84ef-001ec947cd2a")
    private static final String MENU_ID = "com.modeliosoft.modelio.script.InputViewID";

    /**
     * Redo command ID as specified in plugin.xml
     */
    @objid ("008133f0-663d-105c-84ef-001ec947cd2a")
    private static final String REDO_COMMAND_ID = "com.modeliosoft.modelio.script.RedoCommandID";

    /**
     * Select all (Ctrl+A) command ID
     */
    @objid ("00815a6a-663d-105c-84ef-001ec947cd2a")
    private static final String SELECT_ALL_COMMAND_ID = "com.modeliosoft.modelio.script.SelectAllCommandID";

    /**
     * Shift+Tab key binding
     */
    @objid ("00817e8c-663d-105c-84ef-001ec947cd2a")
    private static final String SHIFT_LEFT_COMMAND_ID = "com.modeliosoft.modelio.script.ShiftLeftCommandID";

    /**
     * Tag binding
     */
    @objid ("0081a2fe-663d-105c-84ef-001ec947cd2a")
    private static final String SHIFT_RIGHT_COMMAND_ID = "com.modeliosoft.modelio.script.ShiftRightCommandID";

    /**
     * Undo command ID as specified in plugin.xml
     */
    @objid ("0081c7d4-663d-105c-84ef-001ec947cd2a")
    private static final String UNDO_COMMAND_ID = "com.modeliosoft.modelio.script.UndoCommandID";

    /**
     * All registered actions
     */
    @objid ("00820a8c-663d-105c-84ef-001ec947cd2a")
    private final Map<String , IAction> globalActions = new HashMap<>();

    /**
     * Actions whose state must be updated depending on the selection
     */
    @objid ("00825cbc-663d-105c-84ef-001ec947cd2a")
    private final List<String> selectionDependentActions = new ArrayList<>();

    @objid ("0025a422-572b-1064-a2b8-001ec947cd2a")
    private final ScriptView viewPart;

    @objid ("003db4f4-0dc2-109d-896e-001e4fea2d8b")
    private final Document document;

    /**
     * Mandatory constructor.
     * @param parent parent widget
     * @param styles the SWT style bits for the viewer's control,
     * <em>if <code>SWT.WRAP</code> is set then a custom document adapter needs to be provided, see {@link #createDocumentAdapter()}
     * @param viewPart the view part where the view is contained in
     */
    @objid ("00828d5e-663d-105c-84ef-001ec947cd2a")
    public  InputView(Composite parent, int styles, ScriptView viewPart) {
        super(parent, new CompositeRuler(), styles);
        this.viewPart = viewPart;
        
        SourceViewerConfiguration configuration = new InputViewSourceViewerConfiguration();
        configure(configuration);
        
        // Initialize the document
        this.document = new Document();
        setDocument(this.document);
        
        // Set the font to fixed size font
        Font font = JFaceResources.getFontRegistry().get(JFaceResources.TEXT_FONT);
        getTextWidget().setFont(font);
        
        // Add line numbering column
        CompositeRuler verticalRuler = (CompositeRuler) getVerticalRuler();
        LineNumberRulerColumn column = new LineNumberRulerColumn();
        verticalRuler.addDecorator(0, column);
        
        // Add StyledText key bindings
        getTextWidget().setKeyBinding(SWT.MOD1 | 'A', ST.SELECT_ALL);
        
        // Add key binding handlers
        // initializeKeyBindingHandlers();
        
        // Add drag and drop
        new TextViewerDragDropManager(viewPart, this);
        
    }

    @objid ("0082bcb6-663d-105c-84ef-001ec947cd2a")
    @Override
    public void append(String content) {
        try {
            int offset = getDocument().getLength() - 1;
            int length = 0;
        
            // in case of empty doc
            if (offset < 0) {
                offset = 0;
            }
        
            getDocument().replace(offset, length, content);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
    }

    @objid ("0082e29a-663d-105c-84ef-001ec947cd2a")
    @Override
    public String getText() {
        return getDocument().get();
    }

    /**
     * Get the code to execute and remove it from the input view unless 'debug' is true. <br>
     * The code to execute is:
     * <ul>
     * <li>the whole input view content if there is no selection</li>
     * <li>the selected text if there is a selection. (The selected text will then be removed from the input view if no debug mode.)
     * </li>
     * </ul>
     * @return The code to execute.
     */
    @objid ("008310da-663d-105c-84ef-001ec947cd2a")
    public String popInput(boolean debug) {
        try {
            final Point selection = getSelectedRange();
            if (selection.y == 0) {
                // No selection
                // ==> Execute all
                selection.x = 0;
                selection.y = getDocument().getLength();
        
                // // ==> Execute up to the cursor
                // selection.y = selection.x;
                // selection.x = 0;
        
                final String ret = getDocument().get(selection.x, selection.y);
                // System.out.println("selection to remove=("+selection.toString()+") \nret ='"+ret+"'");
        
                if (!debug) {
                    getDocument().replace(0, selection.y, "");
                }
        
                return ret;
            } else {
                final String ret = getDocument().get(selection.x, selection.y);
        
                if (!debug) {
                    getDocument().replace(selection.x, selection.y, "");
                }
        
                return ret;
        
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
            return "";
        }
        
    }

    /**
     * Fill the contextual menu.
     * @param menu menu to fill.
     */
    @objid ("00833538-663d-105c-84ef-001ec947cd2a")
    protected void fillContextMenu(IMenuManager menu) {
        for (IAction act : this.globalActions.values()) {
            if (act instanceof IUpdate) {
                ((IUpdate) act).update();
            }
        }
        
        menu.add(new GroupMarker(IActionConstants.GROUP_UNDO));
        menu.appendToGroup(IActionConstants.GROUP_UNDO, this.globalActions.get(ActionFactory.UNDO.getId()));
        menu.appendToGroup(IActionConstants.GROUP_UNDO, this.globalActions.get(ActionFactory.REDO.getId()));
        
        menu.add(new Separator(IActionConstants.GROUP_EDIT));
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(ActionFactory.CUT.getId()));
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(ActionFactory.COPY.getId()));
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(ActionFactory.PASTE.getId()));
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(SELECT_ALL_COMMAND_ID));
        
        menu.add(new Separator());
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(SHIFT_RIGHT_COMMAND_ID));
        menu.appendToGroup(IActionConstants.GROUP_EDIT, this.globalActions.get(SHIFT_LEFT_COMMAND_ID));
        
        menu.add(new Separator(IActionConstants.GROUP_EXECUTE));
        // Some commands are added by plugin.xml here.
        // eg : Execute
        
    }

    /**
     * Initialize contextual menu.
     */
    @objid ("0083719c-663d-105c-84ef-001ec947cd2a")
    private void initializeContextualMenu() {
        // Create context menu
        MenuManager manager = new MenuManager(null, MENU_ID);
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager mgr) {
                fillContextMenu(mgr);
            }
        });
        
        // Add the menu to the widget
        StyledText text = getTextWidget();
        Menu menu = manager.createContextMenu(text);
        text.setMenu(menu);
        
        // Allow other plugins to add commands to the menu
        // this.viewPart.getViewSite().registerContextMenu(MENU_ID, manager,
        // InputView.this);
        
    }

    /**
     * Register handlers for key bindings that cannot be done with {@link StyledText#setKeyBinding(int, int)}.
     */
    @objid ("00838880-663d-105c-84ef-001ec947cd2a")
    private void initializeKeyBindingHandlers() {
        // IHandlerService service = (IHandlerService)
        // this.viewPart.getSite().getService(IHandlerService.class);
        //
        // // Add handlers for the undo and redo commands
        // service.activateHandler(UNDO_COMMAND_ID, new UndoCommandHandler());
        // service.activateHandler(REDO_COMMAND_ID, new RedoCommandHandler());
        //
        // // Handlers for Tab and Shift+Tab
        // service.activateHandler(SHIFT_LEFT_COMMAND_ID, new
        // ActionHandler(this.globalActions.get(SHIFT_LEFT_COMMAND_ID)));
        // service.activateHandler(SHIFT_RIGHT_COMMAND_ID, new
        // ActionHandler(this.globalActions.get(SHIFT_RIGHT_COMMAND_ID)));
        // service.activateHandler(SELECT_ALL_COMMAND_ID, new
        // ActionHandler(this.globalActions.get(SELECT_ALL_COMMAND_ID)));
        
    }

    /**
     * Update an action state.
     * @param actionId the ID of the action to update
     */
    @objid ("0083bc92-663d-105c-84ef-001ec947cd2a")
    private void updateAction(String actionId) {
        IAction action = this.globalActions.get(actionId);
        if (action instanceof IUpdate) {
            ((IUpdate) action).update();
        }
        
    }

    /**
     * Update all selection dependent actions.
     */
    @objid ("0083e41a-663d-105c-84ef-001ec947cd2a")
    private void updateSelectionDependentActions() {
        for (String id : this.selectionDependentActions) {
            updateAction(id);
        }
        
    }

    /**
     * Update the undo action.
     */
    @objid ("0083fc0c-663d-105c-84ef-001ec947cd2a")
    private void updateUndoAction() {
        updateAction(ActionFactory.UNDO.getId());
    }

    /**
     * Defines the names of the menu groups in a text editor's context menu.
     * <p>
     * This interface must not be implemented by clients.
     * </p>
     * 
     * @noimplement This interface is not intended to be implemented by clients.
     */
    @objid ("008411ba-663d-105c-84ef-001ec947cd2a")
    private interface IActionConstants {
        /**
         * Context menu group for copy/paste related actions. Value: <code>"group.find"</code>
         */
        @objid ("00842286-663d-105c-84ef-001ec947cd2a")
        public static final String GROUP_EDIT = "group.edit";

        /**
         * Context menu group for find/replace related actions. Value: <code>"group.find"</code>
         */
        @objid ("00844964-663d-105c-84ef-001ec947cd2a")
        public static final String GROUP_FIND = "group.find"; // $NON-NLS-1$
        

        /**
         * Context menu group for undo/redo related actions. Value: <code>"group.undo"</code>
         */
        @objid ("008477ea-663d-105c-84ef-001ec947cd2a")
        public static final String GROUP_UNDO = "group.undo"; // $NON-NLS-1$
        

        @objid ("0084a698-663d-105c-84ef-001ec947cd2a")
        public static final String GROUP_EXECUTE = "group.execute";
}
    

    /**
     * Input viewer configuration.
     * <p>
     * Will configure someday syntax highlighting and auto completion.
     */
    @objid ("0084c36c-663d-105c-84ef-001ec947cd2a")
    protected static class InputViewSourceViewerConfiguration extends SourceViewerConfiguration {
        @objid ("0084e8ce-663d-105c-84ef-001ec947cd2a")
        @Override
        public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
            PresentationReconciler reconciler = new PresentationReconciler();
            
            RuleBasedScanner scanner = new PythonPartitionScanner();
            
            IPresentationRepairer repairer = new DefaultDamagerRepairer(scanner);
            reconciler.setRepairer(repairer, "");
            
            DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
            
            reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
            reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
            return reconciler;
            // return super.getPresentationReconciler(sourceViewer);
        }

        @objid ("008c72e2-b233-1069-96f6-001ec947cd2a")
        static class PythonPartitionScanner extends RuleBasedScanner {
            @objid ("001994de-b235-1069-96f6-001ec947cd2a")
            private static String[] keywords = { "and", "assert", "break", "class", "continue", "def", "del", "elif", "else",
                                "except", "exec", "finally", "for", "from", "global", "if", "import", "in", "is", "lambda", "not", "or",
                                "pass", "print", "raise", "return", "self", "try", "while", "yield" };

            @objid ("005a4fe2-f1bd-106a-bf4f-001ec947cd2a")
            public static final Color KEYWORD_COLOR = new Color(Display.getCurrent(), new RGB(128, 0, 128));

            @objid ("005a5f64-f1bd-106a-bf4f-001ec947cd2a")
            public static final Color COMMENT_COLOR = new Color(Display.getCurrent(), new RGB(0, 128, 0));

            @objid ("005a6f2c-f1bd-106a-bf4f-001ec947cd2a")
            public static final Color CONSTANT_COLOR = new Color(Display.getCurrent(), new RGB(0, 0, 255));

            @objid ("008cceea-b233-1069-96f6-001ec947cd2a")
            public  PythonPartitionScanner() {
                super();
                
                final Font normalFont = Display.getCurrent().getSystemFont();
                FontData[] fontData = normalFont.getFontData();
                for (FontData fd : fontData) {
                    fd.setStyle(SWT.BOLD);
                }
                Font boldFont = new Font(Display.getDefault(), fontData);
                
                final Color foreground = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
                final Color background = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
                
                IToken keyword = new Token(new TextAttribute(KEYWORD_COLOR, background, SWT.NORMAL, boldFont));
                IToken comment = new Token(new TextAttribute(COMMENT_COLOR, background, SWT.NORMAL, normalFont));
                IToken constant = new Token(new TextAttribute(CONSTANT_COLOR, background, SWT.NORMAL, normalFont));
                
                final TextAttribute textAtt = new TextAttribute(foreground, background, SWT.NORMAL, normalFont);
                
                setDefaultReturnToken(new Token(textAtt));
                
                List<IRule> rules = new ArrayList<>();
                
                // Add rule for single line comments.
                rules.add(new EndOfLineRule("#", comment));
                
                // Add rule for strings and character constants.
                rules.add(new SingleLineRule("\"", "\"", constant, '\\'));
                rules.add(new SingleLineRule("'", "'", constant, '\\'));
                
                // Keywords rule
                WordRule wordRule = new WordRule(new IWordDetector() {
                    @Override
                    public boolean isWordStart(char c) {
                        return Character.isLetter(c);
                    }
                
                    @Override
                    public boolean isWordPart(char c) {
                        return Character.isLetter(c);
                    }
                });
                for (String k : keywords) {
                    wordRule.addWord(k, keyword);
                }
                rules.add(wordRule);
                
                // Set rules
                setRules(rules.toArray(new IRule[rules.size()]));
                
            }

        }

    }

    /**
     * Action that can update its state
     */
    @objid ("0085255a-663d-105c-84ef-001ec947cd2a")
    private interface IUpdate {
        @objid ("008534aa-663d-105c-84ef-001ec947cd2a")
        void update();
}
    

}
