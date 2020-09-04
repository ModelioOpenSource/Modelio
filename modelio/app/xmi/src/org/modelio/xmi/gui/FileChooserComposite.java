/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.xmi.gui;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.ui.UIImages;

/**
 * This class defines the file chooser composite.
 * This composite is composed of
 * - a text field in order to specify the desired file
 * - a file browser button for allowing file browsing
 * 
 * It is a SWT composite
 * @author ebrosse
 */
@objid ("1e24f10e-f51c-4ea2-982c-1e1628a06389")
public class FileChooserComposite extends Composite {
    @objid ("9871b0ae-d048-4de7-9bc3-abcd2c64dbeb")
    private File currentFile = null;

    @objid ("a585e771-7681-4e26-a80e-a67ed9237134")
    private Text text = null;

    @objid ("080d1883-77c5-40bc-b539-76ac664a18d0")
    private Button searchButton = null;

    @objid ("9b6d8ee1-4e80-49a5-a01a-ec6dcd6631bd")
    protected FileDialog dialog = null;

    /**
     * This method returns the chosen file
     * @return the path of the chosen file
     */
    @objid ("4605a75d-47ce-4dd3-9e98-c8947a1015a6")
    public File getCurrentFile() {
        String nomFichier = this.text.getText();
        if ((nomFichier != null) && (nomFichier.length() != 0)) {
            this.currentFile = new File(nomFichier);
        } else {
            this.currentFile = null;
        }
        return this.currentFile;
    }

    /**
     * This method allows to set the chosen file
     * @param currentFile : the chosen file
     */
    @objid ("5d963c96-83f5-4db2-b931-e66bf48e99a7")
    public void setCurrentFile(final File currentFile) {
        this.currentFile = currentFile;
    }

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("30032f29-b646-4bc5-94b2-db3675da639c")
    public void setText(final String label) {
        if (label != null)
            this.text.setText(label);
    }

    /**
     * Constructor of the FileChooserComposite.
     * It needs :
     * - the parent composite
     * - its SWT style
     * - the selection type of the SWT FileDialog
     * @param parent : the SWT composite owner
     * @param style : the SWT style
     * @param typeSelection : the SWT selection type
     */
    @objid ("46e26c47-2811-4605-a7e8-35c3f9f88468")
    public FileChooserComposite(final Shell parent, final int style, final int typeSelection) {
        super(parent, style);
        setLayout(new FormLayout());
        this.dialog = new FileDialog(parent, typeSelection);
        
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        this.setLayout(gridLayout);
        
        this.text = new Text(this, SWT.BORDER);
        this.text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        this.text.setEnabled(true);
        this.searchButton = new Button(this, SWT.NONE);
        this.searchButton.setImage(UIImages.FILECHOOSE);
    }

    /**
     * This method launch the folder browsing and returns the path of the chosen file
     * @return the path of the chosen file
     */
    @objid ("19dbfc8f-30d7-4834-b884-67964dae7372")
    public String searchFile() {
        String nomFichier = this.dialog.open();
        if ((nomFichier != null) && (nomFichier.length() != 0)) {
            this.currentFile = new File(nomFichier);
            this.text.setText(nomFichier);
        }
        return this.text.getText();
    }

    /**
     * This method returns the search button of the composite
     * @return the search button
     */
    @objid ("3b7cdbd2-f852-4225-91d4-932221779063")
    public Button getSearch() {
        return this.searchButton;
    }

    /**
     * This methods returns the text available in the SWT FileDialog
     * @return the text of the FileDialog
     */
    @objid ("50095e7b-1f62-40b6-8fbd-b1fd6503333a")
    public String getText() {
        return this.text.getText();
    }

    /**
     * This method returns the SWT Text owned by the FileChooserComposite
     * @return the owned SWT TEXT
     */
    @objid ("006aee3c-4c2f-40ce-9c60-10df94d30594")
    public Text getTextButton() {
        return this.text;
    }

    /**
     * This method returns the SWT FileDialog created inside the FileChooserComposite
     * @return the owned FileDialog
     */
    @objid ("59d4f7a7-847d-437a-92c7-003226fb3d64")
    public FileDialog getDialog() {
        return this.dialog;
    }

}
