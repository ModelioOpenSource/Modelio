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

package org.modelio.patterns.exporter.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.api.modelio.pattern.IPatternService.PatternException;
import org.modelio.metamodel.Metamodel;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.model.IPattern;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.GenericFactory;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * This class is in charge of exporting an executable jar from a {@link RuntimePattern}.
 */
@objid ("c4642b85-bc62-4047-bb08-86ede73aa57a")
public class JarExporter {
    @objid ("05222809-1ee2-4266-a4b6-03420d1ee670")
    public JarExporter() {
    }

    @objid ("f7e4f245-ab22-469a-9bcd-c60f52e3dcb7")
    public boolean makeJar(Path exportDirectory) throws IOException {
        Path jarFile = exportDirectory.resolve("Pattern.jar");
        return JarPackagingHelper.makeJar(exportDirectory, jarFile);
    }

    /**
     * Generate a Pattern.java file from a <<Pattern>> Package.
     * 
     * @return the generated file.
     */
    @objid ("28c273e9-0968-4c34-82a5-d7a7da0892e3")
    public PatternModelAnalysis generateJavaPattern(Package modelPattern, Path exportDir) {
        // Create new java file for the pattern and open it
        try (FileWriterUtil filewriter = new FileWriterUtil(exportDir != null ? exportDir.resolve("Pattern.java") : null)) {
            // Init generators
            MetaGenerator metaGenerator = new MetaGenerator();
        
            // Generate pattern code
            filewriter.write("package org.modelio.patterns.importer;");
        
            filewriter.write("import " + Modelio.class.getCanonicalName() + ";");
            filewriter.write("import " + IModelingSession.class.getCanonicalName() + ";");
            filewriter.write("import " + IMetamodelExtensions.class.getCanonicalName() + ";");
            filewriter.write("import " + IUMLTypes.class.getCanonicalName() + ";");
            filewriter.write("import " + GenericFactory.class.getCanonicalName() + ";");
            filewriter.write("import " + MObject.class.getCanonicalName() + ";");
            filewriter.write("import " + Patterns.class.getCanonicalName() + ";");
            filewriter.write("import " + IPattern.class.getCanonicalName() + ";");
            filewriter.write("import " + PatternException.class.getCanonicalName() + ";");
            filewriter.write("import " + MObject.class.getCanonicalName() + ";");
            filewriter.write("import " + ICoreSession.class.getCanonicalName() + ";");
            filewriter.write("import " + ITransaction.class.getCanonicalName() + ";");
        
            filewriter.write("import org.modelio.metamodel.uml.behavior.activityModel.*;");
            filewriter.write("import org.modelio.metamodel.uml.behavior.commonBehaviors.*;");
            filewriter.write("import org.modelio.metamodel.uml.behavior.communicationModel.*;");
            filewriter.write("import org.modelio.metamodel.uml.behavior.interactionModel.*;");
            filewriter.write("import org.modelio.metamodel.uml.behavior.stateMachineModel.*;");
            filewriter.write("import org.modelio.metamodel.uml.behavior.usecaseModel.*;");
            filewriter.write("import org.modelio.metamodel.uml.informationFlow.*;");
            filewriter.write("import org.modelio.metamodel.uml.infrastructure.*;");
            filewriter.write("import org.modelio.metamodel.uml.infrastructure.properties.*;");
            filewriter.write("import org.modelio.metamodel.uml.statik.*;");
            filewriter.write("import " + Class.class.getCanonicalName() + ";");
            filewriter.write("import " + Package.class.getCanonicalName() + ";");
            filewriter.write("import " + Interface.class.getCanonicalName() + ";");
            filewriter.write("import " + Metamodel.class.getCanonicalName() + ";");
            filewriter.write("import " + AbstractDiagram.class.getCanonicalName() + ";");
        
            filewriter.write("import " + IDiagramHandle.class.getCanonicalName() + ";");
            filewriter.write("import " + LinkRouterKind.class.getCanonicalName() + ";");
            filewriter.write("import " + IDiagramLink.class.getCanonicalName() + ";");
            filewriter.write("import " + IDiagramNode.class.getCanonicalName() + ";");
            filewriter.write("import " + IDiagramGraphic.class.getCanonicalName() + ";");
            filewriter.write("import " + IStyleHandle.class.getCanonicalName() + ";");
            filewriter.write("import " + IDiagramService.class.getCanonicalName() + ";");
        
            filewriter.write("import " + Point.class.getCanonicalName() + ";");
            filewriter.write("import " + Rectangle.class.getCanonicalName() + ";");
        
            filewriter.write("import " + Map.class.getCanonicalName() + ";");
            filewriter.write("import " + HashMap.class.getCanonicalName() + ";");
            filewriter.write("import " + ArrayList.class.getCanonicalName() + ";");
            filewriter.write("import " + Collection.class.getCanonicalName() + ";");
            filewriter.write("import " + List.class.getCanonicalName() + ";");
        
            filewriter.write("");
            filewriter.write("public class Pattern implements IPattern{");
            filewriter.write("");
            filewriter.write("private GenericFactory model;");
            filewriter.write("private MObject root;");
            filewriter.write("private IModelingSession session = Modelio.getInstance().getModelingSession();");
            filewriter.write("private IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();");
            filewriter.write("private IUMLTypes umltypes = this.session.getModel().getUmlTypes();");
            filewriter.write("private ArrayList<MObject> elements = new ArrayList<>();");
            filewriter.write("private Map<String,Object> parameters = new HashMap<>();");
            filewriter.write("private List links = null;");
            filewriter.write("private Collection<Point> points = null;");
            filewriter.write("private org.eclipse.draw2d.geometry.Rectangle rec = null;");
            filewriter.write("private org.eclipse.draw2d.geometry.Point point = null;");
            filewriter.write("private IDiagramHandle rep = null;");
        
            IdGenerator.getInstance().reset();
            metaGenerator.generate(filewriter, modelPattern);
        
            metaGenerator.getRelationGenerator().generate(filewriter);
            metaGenerator.getDiagramGenerator().generate(filewriter);
        
            if (filewriter.getCounter() <= 1000) {
                filewriter.write("}");
            }
            filewriter.write("");
        
            // createModel
            filewriter
                    .write("public void createModel(MObject root, ICoreSession coreSession, Map<String, Object> patternParameters) throws PatternException {");
            filewriter.write("    this.model = coreSession.getModel().getGenericFactory();");
            filewriter.write("    this.metamodel = Modelio.getInstance().getModelingSession().getMetamodelExtensions();");
            filewriter.write("    this.umltypes = Modelio.getInstance().getModelingSession().getModel().getUmlTypes();");
            filewriter.write("    this.root = root;");
            filewriter.write("    this.parameters = patternParameters;");
            filewriter.write("");
            filewriter
                    .write("    try (ITransaction tr = coreSession.getTransactionSupport().createTransaction(\"Implementation\");){");
            filewriter.write("");
            for (int i = 1; (i <= filewriter.getMethodIndex()); i++) {
                filewriter.write("        createModel" + i + "();");
            }
            filewriter.write("");
            filewriter.write("        tr.commit();");
            filewriter.write("    } catch (Exception e) {");
            filewriter.write("        throw new PatternException(e);");
            filewriter.write("    }");
            filewriter.write("}");
            filewriter.write("");
        
            // calculate
            filewriter.write("public void calculate(IDiagramLink node){");
            filewriter.write("    int i = 0;");
            filewriter.write("    ArrayList<Point> points = new ArrayList<>();");
            filewriter.write("    for (Point point : node.getPath().getPoints()) {");
            filewriter.write("        if (i == 0) {");
            filewriter.write("            IDiagramGraphic from = node.getFrom();");
            filewriter.write("            if (from instanceof IDiagramNode) {");
            filewriter.write("                Rectangle bounds = ((IDiagramNode)from).getBounds();");
            filewriter.write("                int x = bounds.x + (bounds.width / 2);");
            filewriter.write("                int y = bounds.y + (bounds.height / 2);");
            filewriter.write("                point = new org.eclipse.draw2d.geometry.Point(x, y);");
            filewriter.write("                points.add(point);");
            filewriter.write("            }");
            filewriter.write("        } else if (i == node.getPath().getPoints().size() - 1) {");
            filewriter.write("            IDiagramGraphic to = node.getTo();");
            filewriter.write("            if (to instanceof IDiagramNode) {");
            filewriter.write("                Rectangle bounds = ((IDiagramNode)to).getBounds();");
            filewriter.write("                int x = bounds.x + (bounds.width / 2);");
            filewriter.write("                int y = bounds.y + (bounds.height / 2);");
            filewriter.write("                point = new org.eclipse.draw2d.geometry.Point(x, y);");
            filewriter.write("                points.add(point);");
            filewriter.write("            }");
            filewriter.write("        } else {");
            filewriter.write("            point = new org.eclipse.draw2d.geometry.Point(point.x, point.y);");
            filewriter.write("            points.add(point);");
            filewriter.write("        }");
            filewriter.write("        i++;");
            filewriter.write("    }");
            filewriter.write("    try {");
            filewriter.write("        node.setPath(points);");
            filewriter.write("    } catch (Exception e1) {");
            filewriter.write("        Patterns.LOG.error(e1);");
            filewriter.write("    }");
            filewriter.write("}");
            filewriter.write("");
        
            // getIStyleHandleByName
            filewriter.write("public IStyleHandle getIStyleHandleByName (String nameOfStyle){");
            filewriter.write("    IDiagramService service = Modelio.getInstance().getDiagramService();");
            filewriter.write("    for (IStyleHandle style : service.listStyles()) {  ");
            filewriter.write("        if ( style.getName().equals(nameOfStyle))  {");
            filewriter.write("            return style;");
            filewriter.write("        }");
            filewriter.write("    }");
            filewriter.write("    return getIStyleHandleByName(\"" + "default" + "\");");
            filewriter.write("}");
            filewriter.write("}");
        
            filewriter.close();
            return metaGenerator.getReport();
        }
    }

    @objid ("bdff2a6a-003d-4f1a-b504-ae44e3b56e69")
    private static class JarPackagingHelper {
        /**
         * Build a Jar file from a folder containing Java source files.
         */
        @objid ("21ec991d-9861-4adc-bdb6-8fbfb1e8d11f")
        public static boolean makeJar(java.nio.file.Path sourceFolderPath, java.nio.file.Path targetJarPath) throws IOException {
            File sourceFolder = sourceFolderPath.toFile();
            // File targetJar = targetJarPath.toFile();
            Path tempDirectory = Files.createTempDirectory("PatternJar");
            try {
                // Get the java compiler
                JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
                if (jc == null) {
                    String msg = Patterns.I18N.getString("Gui.SystemJavaCompiler.Missing");
                    Patterns.LOG.error(msg);
                    MessageDialog.openError(Display.getDefault().getActiveShell(), Patterns.I18N.getString("Gui.ErrorTitle"), msg);
                    return false;
                }
            
                try (StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null)) {
                    // Add all libraries
                    StringBuilder classPath = new StringBuilder();
                    for (File file : JarPackagingHelper.getCompilationLibraries()) {
                        classPath.append(file.getAbsolutePath());
                        classPath.append(File.pathSeparatorChar);
            
                        // Needed for debug mode
                        classPath.append(file.getAbsolutePath());
                        classPath.append("/bin");
                        classPath.append(File.pathSeparatorChar);
                    }
            
                    // Get sources to compile
                    List<File> sourceFiles = JarPackagingHelper.getJavaFiles(sourceFolder);
                    File[] sourceFilesArray = sourceFiles.toArray(new File[sourceFiles.size()]);
                    Iterable<? extends JavaFileObject> fileObjects = sjfm.getJavaFileObjects(sourceFilesArray);
            
                    try {
                        // Compile
                        String[] options = new String[] { "-d", tempDirectory.toString(), "-cp", classPath.toString() }; //$NON-NLS-1$ //$NON-NLS-2$
                        StringWriter compilOut = new StringWriter();
                        Boolean errorCode = jc.getTask(compilOut, sjfm, null, Arrays.asList(options), null, fileObjects).call();
                        sjfm.close();
            
                        boolean ret = errorCode.booleanValue();
                        if (ret) {
                            Files.createDirectories(targetJarPath.getParent());
                            try (OutputStream fos = Files.newOutputStream(targetJarPath);
                                    JarOutputStream out = new JarOutputStream(fos)) {
                                for (String classfile : JarPackagingHelper.getClassPath(tempDirectory, "")) {
                                    out.putNextEntry(new JarEntry(classfile.replace("\\", "/"))); //$NON-NLS-1$
                                    Files.copy(tempDirectory.resolve(classfile), out);
                                    out.closeEntry();
                                }
            
                                out.close();
                            }
                            return true;
                        } else {
                            // Show compilation errors when pattern compilation fails.
                            Patterns.LOG.error("Pattern compilation failed:");
                            Patterns.LOG.error(compilOut.toString());
                            Display.getDefault().asyncExec(() -> {
                                new ErrorDialog(Display.getDefault().getActiveShell(), Patterns.I18N.getString("Gui.ErrorTitle"),
                                        "The pattern compilation failed.", new Status(IStatus.ERROR, Patterns.PLUGIN_ID,
                                                compilOut.toString()),
                                        IStatus.ERROR).open();
                            });
            
                            return false;
                        }
                    } catch (IOException e) {
                        Patterns.LOG.error("Pattern packaging failed:");
                        Patterns.LOG.error(e);
                        MessageDialog.openError(Display.getDefault().getActiveShell(), Patterns.I18N.getString("Gui.ErrorTitle"),
                                FileUtils.getLocalizedMessage(e));
                        return false;
                    }
                }
            } finally {
                // Delete temp directory
                if (Files.exists(tempDirectory)) {
                    FileUtils.delete(tempDirectory);
                }
            }
        }

        @objid ("c7523b11-9cc9-4695-bb92-60f480ec4d17")
        private static List<String> getClassPath(Path sourceDirectory, String classpath) {
            List<String> res = new ArrayList<>();
            for (File subfile : sourceDirectory.toFile().listFiles()) {
                String newPath;
                if (subfile.isDirectory()) {
                    if (!classpath.equals("")) {
                        newPath = classpath + "/" + subfile.getName();
                    } else {
                        newPath = subfile.getName();
                    }
                    res.addAll(JarPackagingHelper.getClassPath(subfile.toPath(), newPath));
                } else {
                    if (subfile.getName().endsWith(".class")) {
                        res.add(classpath + "/" + subfile.getName());
                    }
                }
            }
            return res;
        }

        @objid ("a5cf8198-84ea-40bf-8abf-ff606daa0397")
        private static List<File> getJavaFiles(File sourceDirectory) {
            List<File> res = new ArrayList<>();
            for (File subfile : sourceDirectory.listFiles()) {
                if (subfile.isDirectory()) {
                    res.addAll(JarPackagingHelper.getJavaFiles(subfile));
                } else {
                    if (subfile.getName().endsWith(".java")) {
                        res.add(subfile);
                    }
                }
            }
            return res;
        }

        /**
         * Get the minimal list of libraries needed to compile a pattern.
         */
        @objid ("5a317307-440d-41cd-a05e-2c8f6ac17c82")
        private static List<File> getCompilationLibraries() {
            List<File> libraries = new ArrayList<>();
            
            // For now, all libraries are plugins available in the execution environment
            for (String pluginId : Arrays.asList("com.modeliosoft.modelio.analyst.metamodel.api",
                    "org.modelio.core.metamodel.api",
                    "org.eclipse.draw2d",
                    "org.eclipse.emf.common",
                    "org.eclipse.emf.ecore",
                    "org.eclipse.equinox.common",
                    "org.modelio.archimate.metamodel.api",
                    "org.modelio.bpmn.metamodel.api",
                    "org.modelio.core.kernel",
                    "org.modelio.core.session",
                    "org.modelio.platform.api",
                    "org.modelio.platform.utils",
                    "org.modelio.uml.metamodel.api",
                    Patterns.PLUGIN_ID)) {
                File pluginFile = JarPackagingHelper.getPluginClasspath(pluginId);
                if (pluginFile != null) {
                    libraries.add(pluginFile);
                }
            }
            return libraries;
        }

        /**
         * Get a plugin's jar path from its id.
         */
        @objid ("42ec1916-c0a2-4328-adc7-435274303a31")
        private static File getPluginClasspath(String pluginId) {
            Bundle emfCommonBundle = Platform.getBundle(pluginId);
            if (emfCommonBundle != null) {
                URL emfCommonUrl = FileLocator.find(emfCommonBundle, new org.eclipse.core.runtime.Path(""), null);
                File emfCommonFile;
                try {
                    URL localUrl = FileLocator.toFileURL(emfCommonUrl);
                    emfCommonFile = new File(localUrl.getPath());
                } catch (IOException e) {
                    emfCommonFile = new File(emfCommonUrl.getPath());
                }
                return emfCommonFile;
            } else {
                return null;
            }
        }

    }

}
