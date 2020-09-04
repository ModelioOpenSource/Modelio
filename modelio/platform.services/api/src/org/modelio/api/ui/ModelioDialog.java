/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog template that has :
 * <ul>
 * <li>a header with :
 * <ul>
 * <li>a left image, modifiable with {@link #setTitleLeftImage(Image)}
 * <li>a bold title, set with {@link #setTitle(String)}
 * <li>a logo image displayed on the right side, set with {@link #setLogoImage(Image)}
 * <li>a message, set with {@link #setMessage(String)}.
 * </ul>
 * <li>a user defined content area defined with {@link #createContentArea(Composite)}.
 * <li>a button area with user defined buttons defined with {@link #addButtonsInButtonBar(Composite)}.
 * <li>An optional message area that display on top of the header area, showing a warning or error small icon and a message.<br>
 * See {@link #setWarningMessage(String)} and {@link #setErrorMessage(String)}.
 * </ul>
 * <p>
 * Subclasses typical implementation:
 * 
 * <pre>
 * public class ExampleDialog extends ModelioDialog {
 * protected ExampleDialog(Shell parentShell) {
 * super(parentShell);
 * setBlockOnOpen(false);
 * }
 * 
 * &#64;Override
 * public Control createContentArea(Composite parent) {
 * Composite composite = new Composite(parent, SWT.NONE);
 * composite.setLayoutData(new GridData(GridData.FILL_BOTH));
 * 
 * composite.setLayout(new FillLayout());
 * // add controls to composite as necessary
 * Label label = new Label(composite, SWT.NONE);
 * label.setText("Dialog content is here");
 * 
 * return composite;
 * }
 * 
 * &#64;Override
 * protected void addButtonsInButtonBar(Composite parent) {
 * addDefaultButtons(parent);
 * }
 * 
 * &#64;Override
 * public void init() {
 * setTitle("ExampleDialog");
 * setMessage("A very simple dialog");
 * }
 * 
 * &#64;Override
 * protected Point getInitialSize() {
 * return new Point(150, 150);
 * }
 * 
 * &#64;Override
 * protected void configureShell(Shell newShell) {
 * super.configureShell(newShell);
 * setTitle("Shell title");
 * }
 * }
 * </pre>
 * </p>
 */
@objid ("7c12d54a-704b-11dd-933a-001ec947cd2a")
public abstract class ModelioDialog extends org.modelio.ui.dialog.ModelioDialog {
    @objid ("bc2b30ad-120f-11e2-b5c6-002564c97630")
    protected ModelioDialog(final Shell parentShell) {
        super(parentShell);
    }

}
