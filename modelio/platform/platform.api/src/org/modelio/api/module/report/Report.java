/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.api.module.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A Report is a sequence of ReportEntry records.
 * 
 * Beside storing the recorded ReportEntry instances, this class mainly provides several convenience methods to add errors, warnings tips and infos to the report.
 * 
 * See @link {@link Report#addError(int, String, MObject...)} and similar methods.
 * 
 * 
 * <p>
 * Note about the use of <code>varargs</code> for the related MObject objects:
 * 
 * <pre>
 * // To add objects obj1, obj2 and obj3, simply pass the objects to the addXXX() call
 * addError(1001, "My formatted message with three related objects", obj1, obj2 obj3);
 * 
 * // For 'no objects' , do not pass 'null' but rather pass nothing
 * addError(1001, "My formatted message with no related objects");
 * </pre>
 * </p>
 * 
 * @since 4.1
 */
@objid ("5b591047-7ca3-4845-9549-41fe2b282e35")
public class Report {
    @objid ("58221733-d527-4381-bbe2-1cc68cd38963")
    private String title;

    @objid ("209bc2d3-57d8-47af-baf1-8c61f77ec0b4")
    private int nErrors = 0;

    @objid ("824a51a9-12cc-43e7-be2e-d8f4fda91021")
    private int nWarnings = 0;

    /**
     * The ReportEntries stored in the order of their occurrence.
     */
    @objid ("e648d6ca-9d30-4e3a-ba13-afb30e6dfa47")
    private List<ReportEntry> entries = new ArrayList<>();

    @objid ("9a1ad478-f168-4997-88cf-65434d247e12")
    @Deprecated
    public void addError(int code, String message, MObject... mObjects) {
        addError(code, null, null, message, mObjects);
    }

    /**
     * Adds an WARNING message to the Report. The added warning message has no category and no help topic.
     * @param code - the message code (mandatory)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the warning occured or was detected.
     */
    @objid ("0a05752f-0d01-432a-8abd-88aca6f9b260")
    public void addWarning(String code, String message, MObject... mObjects) {
        addWarning(code, null, null, message, mObjects);
    }

    /**
     * Adds an INFO message to the Report.
     * @param message - the formatted message, with I18n and parameter substitution already done.
     */
    @objid ("6156407d-367e-459e-84bd-b8498e9063f2")
    public void addInfo(String message) {
        this.entries.add(new ReportEntry(EntryKind.INFO, "", null, null, message, null));
    }

    @objid ("2c7b8859-877f-4827-9e3c-b85f62e45029")
    public List<ReportEntry> getEntries() {
        return Collections.unmodifiableList(this.entries);
    }

    @objid ("11281deb-865a-42d9-a1d7-054b0975a304")
    public  Report(String title) {
        this.title = title;
    }

    @objid ("37d96e9a-0a4b-4b91-acec-2998985e49b5")
    public String getTitle() {
        return this.title;
    }

    /**
     * Adds an TIP message to the Report. The added tip message has no category and no help topic.
     * @param code - the message code (mandatory)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the tip was identified.
     */
    @objid ("f6e901d8-f1ac-4217-a3f5-07ec28ff3ac4")
    public void addTip(String code, String message, MObject... mObjects) {
        addTip(code, null, null, message, mObjects);
    }

    @objid ("73f43bdb-ccb3-4c9f-ae0c-2b75f9cf83dc")
    @Deprecated
    public void addError(int code, String category, String message, MObject... mObjects) {
        addError(code, category, null, message, mObjects);
    }

    @objid ("93b299b4-316b-4e41-9094-e74d23cfc09d")
    @Deprecated
    public void addError(int code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.ERROR, code, category, helpUrl, message, mObjects));
        this.nErrors++;
        
    }

    /**
     * Adds an WARNING message to the Report. The added warning message has no help topic.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the warning occured or was detected.
     */
    @objid ("8332efcf-b12d-45c5-99d6-1bafa274ccfd")
    public void addWarning(String code, String category, String message, MObject... mObjects) {
        addWarning(code, category, null, message, mObjects);
    }

    /**
     * Adds an WARNING message to the Report.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param helpUrl - the message help topic in the Modelio Help (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the warning occured or was detected.
     */
    @objid ("aad39478-07cc-4230-8831-b49949bc9cd3")
    public void addWarning(String code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.WARNING, code, category, helpUrl, message, mObjects));
        this.nWarnings++;
        
    }

    /**
     * Adds an TIP message to the Report. The added tip message has no help topic.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the tip was identified.
     */
    @objid ("1ae616a8-7fd3-4a1d-9e8c-7aa37d7f222a")
    public void addTip(String code, String category, String message, MObject... mObjects) {
        addTip(code, category, null, message, mObjects);
    }

    /**
     * Adds an TIP message to the Report.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param helpUrl - the message help topic in the Modelio Help (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the tip was identified.
     */
    @objid ("e2f2733e-9e1d-4d4e-9884-1e25cc44b130")
    public void addTip(String code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.TIP, code, category, helpUrl, message, mObjects));
    }

    /**
     * Adds an ERROR message to the Report. The added error message has no category and no help topic.
     * @param code - the message code (mandatory)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the error occured or was detected.
     */
    @objid ("666c12a7-0fa4-4f7f-8f6e-bae17b1c0598")
    public void addError(String code, String message, MObject... mObjects) {
        addError(code, null, null, message, mObjects);
    }

    /**
     * Adds an ERROR message to the Report. The added error message has no help topic.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the error occured or was detected.
     */
    @objid ("83958164-90a5-447c-82cb-7b5c459f2608")
    public void addError(String code, String category, String message, MObject... mObjects) {
        addError(code, category, null, message, mObjects);
    }

    /**
     * Adds an ERROR message to the Report.
     * @param code - the message code (mandatory)
     * @param category - the message category (may be <code>null</code>)
     * @param helpUrl - the message help topic in the Modelio Help (may be <code>null</code>)
     * @param message - the formatted message, with I18n and parameter substitution already done.
     * @param mObjects - the model elements related to this entry. By convention the first one is the 'main' element where the error occured or was detected.
     */
    @objid ("7d62f11c-e00f-4c35-9855-4486498efd94")
    public void addError(String code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.ERROR, code, category, helpUrl, message, mObjects));
        this.nErrors++;
        
    }

    @objid ("4e6e6f34-4c1e-483e-a8b1-6fcc451d7ac9")
    @Deprecated
    public void addTip(int code, String message, MObject... mObjects) {
        addTip(Integer.toString(code), null, null, message, mObjects);
    }

    @objid ("f05db19b-abf0-4a82-9ea9-5a7636867d03")
    @Deprecated
    public void addTip(int code, String category, String message, MObject... mObjects) {
        addTip(code, category, null, message, mObjects);
    }

    @objid ("859aa520-37d1-4d3f-9467-635bbdcfcea7")
    @Deprecated
    public void addTip(int code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.TIP, code, category, helpUrl, message, mObjects));
    }

    @objid ("70b28334-0639-40ba-ba11-e76a1e71b798")
    @Deprecated
    public void addWarning(int code, String message, MObject... mObjects) {
        addWarning(code, null, null, message, mObjects);
    }

    @objid ("4f1748bf-fc82-4910-9588-464d3252aeb0")
    @Deprecated
    public void addWarning(int code, String category, String message, MObject... mObjects) {
        addWarning(code, category, null, message, mObjects);
    }

    @objid ("763e1d39-967e-45d4-b785-7be71034c31f")
    @Deprecated
    public void addWarning(int code, String category, String helpUrl, String message, MObject... mObjects) {
        this.entries.add(new ReportEntry(EntryKind.WARNING, code, category, helpUrl, message, mObjects));
        this.nWarnings++;
        
    }

    /**
     * @return true if this report contains at least one ERROR entry
     */
    @objid ("61959192-b570-409c-99e9-2079100c5313")
    public boolean hasErrors() {
        return this.nErrors != 0;
    }

    /**
     * @return true if this report contains at least one WARNING entry
     */
    @objid ("cfbe5b0f-e426-42e8-989f-e470604299d7")
    public boolean hasWarnings() {
        return this.nWarnings != 0;
    }

    @objid ("951a306e-09a1-4f7c-ab47-b676f49f9849")
    public void clear() {
        this.entries.clear();
        this.nErrors=0;
        this.nWarnings=0;
        
    }

}
