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

package org.modelio.mda.infra.service.impl.controller.states.features;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.tools.IAttachedBoxTool;
import org.modelio.api.modelio.diagram.tools.IBoxTool;
import org.modelio.api.modelio.diagram.tools.IDiagramTool;
import org.modelio.api.modelio.diagram.tools.ILinkTool;
import org.modelio.api.modelio.diagram.tools.IMultiLinkTool;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.mda.infra.service.IRTModule.DiagramCustomizationDescriptor;
import org.modelio.mda.infra.service.IRTModule.DiagramToolDescriptor;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.mda.infra.service.contributions.WizardContribution;
import org.modelio.mda.infra.service.impl.IRTModuleAccess;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Register all diagram contributions.
 */
@objid ("867c2d42-5710-4ac1-b20d-bcc5b3ce1645")
public class GuiContribFeature extends AbstractFeature {
    @objid ("08ac2138-1918-4166-a198-2dcfdacaacb9")
    @Override
    public void enable() {
        addDiagramTools();
        addDiagramCustomizers();
        addDiagramWizardContributions();
    }

    @objid ("bfd7859d-38e9-4752-bf41-f7b33f48ae44")
    @Override
    public void disable() {
        removeDiagramCustomizers();
        removeDiagramTools();
        removeDiagramWizardContributions();
    }

    /**
     * @param module the module
     */
    @objid ("4682dd3a-0c51-47c5-898c-d49c499e3dba")
    public GuiContribFeature(IRTModuleAccess module) {
        super(module);
    }

    @objid ("0bb7e155-8ae1-4d86-99c1-d19dd2414f37")
    private void addDiagramCustomizers() {
        if (this.module.getDiagramCustomizations().size() > 0) {
            Display.getDefault().syncExec(new DiagramCustomizerAdder(this.module));
        }
    }

    @objid ("12395d6a-01fb-4b02-bfc4-cd60cdf493e9")
    private void removeDiagramCustomizers() {
        if (this.module.getDiagramCustomizations().size() > 0) {
            Display.getDefault().syncExec(new DiagramCustomizerRemover(this.module));
        }
    }

    @objid ("1c81e84d-36e5-469b-9bda-975b7b7ce2b5")
    private void addDiagramTools() {
        if (this.module.getDiagramTools().size() > 0) {
            Display.getDefault().syncExec(new DiagramToolsAdder(this.module));
        }
    }

    @objid ("177559a0-54d4-48e5-9e57-dc0e79a1e136")
    private void removeDiagramTools() {
        if (this.module.getDiagramTools().size() > 0) {
            Display.getDefault().syncExec(new DiagramToolsRemover(this.module));
        }
    }

    @objid ("cccb61ac-5aed-44c9-ae54-957b2eb4ae53")
    private void addDiagramWizardContributions() {
        if (this.module.getWizardContributions().size() > 0) {
            Display.getDefault().syncExec(new DiagramWizardContributionAdder(this.module));
        }
    }

    @objid ("67885298-810a-42b6-ac89-b9b7289cc42c")
    private void removeDiagramWizardContributions() {
        if (this.module.getWizardContributions().size() > 0) {
            Display.getDefault().syncExec(new DiagramWizardContributionRemover(this.module));
        }
    }

    @objid ("5248dd16-ebd6-4066-a7a0-a582b7280909")
    @Override
    public String toString() {
        return "Diagram contributions";
    }

    @objid ("d58428e4-85c9-4f9b-b35f-d52c0f098280")
    private static class DiagramCustomizerAdder implements Runnable {
        @objid ("be550c98-8d06-4110-a64e-2bdd8790ebc2")
        private final IRTModule rtModule;

        @objid ("ccd162f5-a720-41b2-a260-101782305bbd")
        public DiagramCustomizerAdder(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("9e93bfc7-784e-45dd-a0e0-05a3ed77ce85")
        @Override
        public void run() {
            final IDiagramService diagramService = this.rtModule.getIModule().getModuleContext().getModelioServices().getDiagramService();
            final List<DiagramCustomizationDescriptor> diagramCustomizations = this.rtModule.getDiagramCustomizations();
            final MMetamodel mm = this.rtModule.getModel().getMClass().getMetamodel();
            for (DiagramCustomizationDescriptor descriptor : diagramCustomizations) {
                diagramService.registerDiagramCustomization(descriptor.getStereotype(),
                        mm.getMClass(descriptor.getBaseDiagramClass()), descriptor.getCustomizer());
            }
        }

    }

    @objid ("acc95f66-dbf4-435c-9080-be79feb190d0")
    private static class DiagramCustomizerRemover implements Runnable {
        @objid ("96c281c2-fa2d-4e4f-97f2-e3a00f489165")
        private final IRTModule rtModule;

        @objid ("fed81089-d181-4687-92cb-858e744a01b8")
        public DiagramCustomizerRemover(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("94699e3a-2405-4eb1-99af-1eb7aede66e7")
        @Override
        public void run() {
            final IDiagramService diagramService = this.rtModule.getIModule().getModuleContext().getModelioServices().getDiagramService();
            final List<DiagramCustomizationDescriptor> diagramCustomizations = this.rtModule.getDiagramCustomizations();
            final MMetamodel mm = this.rtModule.getModel().getMClass().getMetamodel();
            for (DiagramCustomizationDescriptor descriptor : diagramCustomizations) {
                diagramService.unregisterDiagramCustomization(descriptor.getStereotype(),
                        mm.getMClass(descriptor.getBaseDiagramClass()), descriptor.getCustomizer());
            }
        }

    }

    @objid ("50706ca5-6045-4323-8fb9-df10be4a1d3e")
    private static class DiagramToolsAdder implements Runnable {
        @objid ("4ca296cc-0d1b-42bc-82d0-248c850146a0")
        private final IRTModule rtModule;

        @objid ("971ebb0a-1ace-4b6e-83ce-8decf803c676")
        public DiagramToolsAdder(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("eb95c3e1-141f-440e-99f0-beb70b3eba01")
        @Override
        public void run() {
            final IDiagramService diagramService = this.rtModule.getIModule().getModuleContext().getModelioServices().getDiagramService();
            final List<DiagramToolDescriptor> diagramTools = this.rtModule.getDiagramTools();
            final MMetamodel mm = this.rtModule.getModel().getMClass().getMetamodel();
            
            for (DiagramToolDescriptor toolDescriptor : diagramTools) {
                final IDiagramTool handler = toolDescriptor.getHandler();
                if (handler instanceof IAttachedBoxTool) {
            
                    diagramService.registerCustomizedTool(toolDescriptor.getId(),
                            mm.getMClass(toolDescriptor.getMetaclass()), toolDescriptor.getStereotype(),
                            toolDescriptor.getDep(), (IAttachedBoxTool) handler);
                } else if (handler instanceof IBoxTool) {
                    diagramService.registerCustomizedTool(toolDescriptor.getId(),
                            mm.getMClass(toolDescriptor.getMetaclass()), toolDescriptor.getStereotype(),
                            toolDescriptor.getDep(), (IBoxTool) handler);
                } else if (handler instanceof ILinkTool) {
                    diagramService.registerCustomizedTool(toolDescriptor.getId(),
                            mm.getMClass(toolDescriptor.getMetaclass()), toolDescriptor.getStereotype(),
                            toolDescriptor.getDep(), (ILinkTool) handler);
                } else if (handler instanceof IMultiLinkTool) {
                    diagramService.registerCustomizedTool(toolDescriptor.getId(),
                            mm.getMClass(toolDescriptor.getMetaclass()), toolDescriptor.getStereotype(),
                            toolDescriptor.getDep(), (IMultiLinkTool) handler);
                }
            }
        }

    }

    @objid ("b9b3fd83-be09-4562-b27a-d1c8c35c2768")
    private static class DiagramToolsRemover implements Runnable {
        @objid ("a8bd8cff-9070-49d8-803f-e418c8311d65")
        private final IRTModule rtModule;

        @objid ("770d331b-5350-4d91-a8bf-45a3fa659ad9")
        public DiagramToolsRemover(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("34a00ca6-d701-4020-95ca-2f806cf52873")
        @Override
        public void run() {
            final IDiagramService diagramService = this.rtModule.getIModule().getModuleContext().getModelioServices().getDiagramService();
            final List<DiagramToolDescriptor> diagramTools = this.rtModule.getDiagramTools();
            for (DiagramToolDescriptor toolDescriptor : diagramTools) {
                diagramService.unregisterCustomizedTool(toolDescriptor.getId());
            }
        }

    }

    @objid ("c06988da-9697-41b5-9c2f-fbf9f4fecd7c")
    private static class DiagramWizardContributionAdder implements Runnable {
        @objid ("8251c396-cda0-41df-b90f-352728100138")
        private final IRTModule rtModule;

        @objid ("4234c0fc-8118-41e3-a2b1-a8d69e8f8103")
        public DiagramWizardContributionAdder(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("b69e69da-4ac5-488c-93f1-206d2d20d56f")
        @Override
        public void run() {
            final IEditionService editionService = this.rtModule.getIModule().getModuleContext().getModelioServices().getEditionService();
            final List<WizardContribution> wizardContributions = this.rtModule.getWizardContributions();
            for (WizardContribution contribution : wizardContributions) {
                editionService.registerDiagramContributor(contribution.getCategory(), contribution.getContributor());
            }
        }

    }

    @objid ("fac6b5a2-4b13-4326-8686-e5fc4a775be8")
    private static class DiagramWizardContributionRemover implements Runnable {
        @objid ("a668e635-4226-42f3-b4ef-3f0069a5b227")
        private final IRTModule rtModule;

        @objid ("545985e6-abd0-4e6f-a0dd-1672f70b56dc")
        public DiagramWizardContributionRemover(final IRTModule rtModule) {
            this.rtModule = rtModule;
        }

        @objid ("1d7d7ece-823b-4699-92bb-96c0170d6156")
        @Override
        public void run() {
            final IEditionService editionService = this.rtModule.getIModule().getModuleContext().getModelioServices().getEditionService();
            final List<WizardContribution> diagramWizardContributions = this.rtModule.getWizardContributions();
            for (WizardContribution contribution : diagramWizardContributions) {
                editionService.unregisterDiagramContributor(contribution.getCategory(), contribution.getContributor());
            }
        }

    }

}
