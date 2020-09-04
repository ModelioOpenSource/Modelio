/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.module.command.standard;

import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.modelio.IModelioServices;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.pattern.IPatternService;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command handler to apply a pattern.
 * <p>
 * Registered as default command handler for "PatternApply" module command verb.
 * <p>
 * The run pattern will have access to the following parameters:
 * <ul>
 * <li>The pattern name : the selected element
 * <li>{@value #PATTERNPARAM_SEL} : the selected element
 * <li>{@value #PATTERNPARAM_SEL_NAME} : the selected element name
 * <li>{@value #PATTERNPARAM_MODULE} : the current module
 * </ul>
 * 
 * Used handler parameters:
 * <ul>
 * <li> {@value #COMMANDPARAM_PATTERN_NAME} : the pattern name. Used to set a pattern parameter with the pattern
 * name as key and the Modelio selected element as value
 * <li> {@value #COMMANDPARAM_PATTERN_PATH} : the pattern file path relative to the module resources directory.
 * </ul>
 * 
 * @since 3.4
 */
@objid ("87443439-418d-46e1-8a8f-3c8a5d15ae2f")
public class ApplyPatternStandardHandler extends DefaultModuleCommandHandler {
    /**
     * Command parameter containing the path of the pattern to run.
     * <p>
     * The path must be relative to the module resources path.
     */
    @objid ("fd143aef-447d-4b4f-b48f-07cab7adc298")
    public static final String COMMANDPARAM_PATTERN_PATH = "pattern_path";

    /**
     * Command parameter containing the name of the pattern to run.
     */
    @objid ("a27c713c-8f9d-4133-83f8-6bf7ddcbb6b1")
    public static final String COMMANDPARAM_PATTERN_NAME = "pattern_name";

    /**
     * Pattern parameter name containing the current module.
     */
    @objid ("39157271-99c6-4d00-ac13-1ec1ff71d6f1")
    protected static final String PATTERNPARAM_MODULE = "module";

    /**
     * Pattern parameter name containing the selected element name.
     */
    @objid ("e2b02186-6854-4dbd-b344-f6e79c031197")
    protected static final String PATTERNPARAM_SEL_NAME = "selectionName";

    /**
     * Pattern parameter name containing the selected element on which the pattern is run.
     */
    @objid ("cf664185-d6a6-4813-9539-75a63056f92f")
    protected static final String PATTERNPARAM_SEL = "selection";

    @objid ("bdd510fa-f760-4789-9ec3-709a4480ef0c")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        Shell current = Display.getCurrent().getActiveShell();
        IModelioServices modelioServices = module.getModuleContext().getModelioServices();
        IPatternService svc = modelioServices.getPatternService();
        
        String patternRelPath = getParameters().get(COMMANDPARAM_PATTERN_PATH);
        
        try (ITransaction tr = module.getModuleContext().getModelingSession().createTransaction("Apply "+patternRelPath+" pattern")){
            Map<String, Object> pattParams = configure(selectedElements, module);
        
            Path patternPath = module.getModuleContext().getConfiguration().getModuleResourcesPath().resolve(patternRelPath);
        
            svc.applyPattern(patternPath, pattParams);
        
            postConfigure(selectedElements, module);
        
            tr.commit();
        } catch (InvalidParameterException e) {
            MessageDialog.openError(current, module.getLabel(), e.getLocalizedMessage());
        } catch (IOException e) {
            MessageDialog.openError(current, module.getLabel(), FileUtils.getLocalizedMessage(e));
        }
    }

    /**
     * Configure the command before being executed.
     * <p>
     * This method is a hook called by {@link #actionPerformed(List, IModule)}.
     * It returns the parameters the pattern will use.
     * <p>
     * By default copy all the command handler parameters and add the following ones:<ul>
     * <li>selection : the selectedElements selection
     * <li>module : the IModule.
     * </ul>
     * @param selectedElements the current selection in Modelio
     * @param module the module owning the command handler.
     */
    @objid ("cbf81673-0b42-4bae-a4fb-dbc71bac5582")
    protected Map<String, Object> configure(List<MObject> selectedElements, IModule module) {
        // Copy command parameters to pattern parameters
        Map<String, Object> pattParams = new HashMap<>(getParameters());
        
        // add selected element, its name and the module
        pattParams.put(getParameter(COMMANDPARAM_PATTERN_NAME), selectedElements.get(0));
        pattParams.put(PATTERNPARAM_SEL, selectedElements.get(0));
        pattParams.put(PATTERNPARAM_SEL_NAME, selectedElements.get(0).getName());
        pattParams.put(PATTERNPARAM_MODULE, module);
        return pattParams;
    }

    /**
     * Hook called once the pattern is applied and before the transaction is committed.
     * <p>
     * Does nothing by default. Sub classes may redefine this method to make additional modifications.
     * @param selectedElements the current selection in Modelio
     * @param module the module owning the command handler.
     */
    @objid ("f668f7b3-f9eb-4a48-9fd8-c7c0c1c97d53")
    protected void postConfigure(List<MObject> selectedElements, IModule module) {
        // nothing by default
    }

    @objid ("552c501f-d08d-4657-8794-d072d2de79d7")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        // only one element must be selected
        if (selectedElements.size() != 1) {
            return false;
        }
        return super.accept(selectedElements, module);
    }

}
