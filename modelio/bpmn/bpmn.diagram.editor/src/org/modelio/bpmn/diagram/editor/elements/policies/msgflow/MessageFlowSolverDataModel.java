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

package org.modelio.bpmn.diagram.editor.elements.policies.msgflow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.rootElements.BpmnBaseElement;

@objid ("e3bbb16b-c077-43d0-bf53-c1638c0c9187")
public class MessageFlowSolverDataModel {
    @objid ("1b2af321-5eee-49e7-bfd6-8c217900d582")
    private String messageFlowName = "Message Flow";

    @objid ("44290430-9754-4ac4-b8ee-03a9d24af0ae")
    private List<BpmnBaseElement> sources;

    @objid ("254185c0-72ce-44f8-919f-899699dcbaf1")
    private List<BpmnBaseElement> targets;

    @objid ("298a2603-7aaa-446c-9f53-98778456d8e8")
    private BpmnBaseElement selectedSource;

    @objid ("b6e21bf1-b898-4dc3-8e29-a2b16e1fdbb3")
    private BpmnBaseElement selectedTarget;

    @objid ("ca6909c2-c9fe-4f4c-b4a4-002bccae6634")
    private BpmnBaseElement sourcesProvider;

    @objid ("fe2abb6c-0873-4325-9d80-95d28d0feac4")
    private BpmnBaseElement targetsProvider;

    @objid ("76c6b8f4-bf1a-47ec-a557-93fa7f275ec1")
    public List<BpmnBaseElement> getSources() {
        return this.sources;
    }

    @objid ("cd33b6d1-348c-4c24-873c-bc3454e6e193")
    public List<BpmnBaseElement> getTargets() {
        return this.targets;
    }

    @objid ("55909b7e-5792-42ed-b2d2-aaf5b41cdb70")
    public BpmnBaseElement getSelectedSource() {
        return this.selectedSource;
    }

    @objid ("154529c6-acad-44aa-87d9-f6aec5518b50")
    public BpmnBaseElement getSelectedTarget() {
        return this.selectedTarget;
    }

    @objid ("13fc315d-b335-42c8-b54f-693992c79460")
    public void setSelectedSource(BpmnBaseElement selectedSource) {
        this.selectedSource = selectedSource;
    }

    @objid ("16b91c23-15d9-454c-80df-1603b9dfb523")
    public void setSelectedTarget(BpmnBaseElement selectedTarget) {
        this.selectedTarget = selectedTarget;
    }

    @objid ("00db2686-915e-4ce5-9f9c-9669be35fc53")
    public BpmnBaseElement getSourcesProvider() {
        return this.sourcesProvider;
    }

    @objid ("1d96175f-0d0c-45dc-bea0-149039a543b4")
    public BpmnBaseElement getTargetsProvider() {
        return this.targetsProvider;
    }

    @objid ("565ca9a6-5234-4d86-ae10-60618c42e634")
    public MessageFlowSolverDataModel(BpmnBaseElement sourcesProvider, List<BpmnBaseElement> sourceCandidates, BpmnBaseElement targetsProvider, List<BpmnBaseElement> targetCandidates) {
        this.sources = new ArrayList<>(sourceCandidates);
        
        // Sort the source candidates by alphabetical order BUT keep the source provider the first element of the list
        this.sources.sort(new Comparator<BpmnBaseElement>() {
            @Override
            public int compare(BpmnBaseElement e1, BpmnBaseElement e2) {
        
                if (e1.equals(sourcesProvider)) {
                    if (e2.equals(sourcesProvider)) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if (e2.equals(sourcesProvider)) {
                    if (e1.equals(sourcesProvider)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return e1.getName().compareTo(e2.getName());
                }
        
            }
        
        });
        
        this.sourcesProvider = sourcesProvider;
        this.selectedSource = this.sources.isEmpty() ? null : this.sources.get(0);
        
        this.targets = new ArrayList<>(targetCandidates);
        // Sort the target candidates by alphabetical order BUT keep the target provider the first element of the list
        this.targets.sort(new Comparator<BpmnBaseElement>() {
            @Override
            public int compare(BpmnBaseElement e1, BpmnBaseElement e2) {
        
                if (e1.equals(targetsProvider)) {
                    if (e2.equals(targetsProvider)) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else if (e2.equals(targetsProvider)) {
                    if (e1.equals(targetsProvider)) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return e1.getName().compareTo(e2.getName());
                }
        
            }
        
        });
        this.targetsProvider = targetsProvider;
        this.selectedTarget = this.targets.isEmpty() ? null : this.targets.get(0);
    }

    @objid ("22c4cb3a-c02a-46dc-b7a8-af129f1758bd")
    public String getMessageFlowName() {
        return this.messageFlowName;
    }

    @objid ("3b5cf87f-e2e4-46e8-8012-3e8dd85aeb6e")
    public void setMessageFlowName(String messageFlowName) {
        this.messageFlowName = messageFlowName;
    }

}
