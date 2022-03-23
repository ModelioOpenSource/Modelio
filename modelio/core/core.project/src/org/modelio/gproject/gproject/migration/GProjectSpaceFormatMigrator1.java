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
package org.modelio.gproject.gproject.migration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorWriter;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.plugin.CoreProject;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.SubProgress;

/**
 * Migrate a GProject directory from format 3 to 4.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("c0525880-4238-4665-8762-cf658022037b")
public class GProjectSpaceFormatMigrator1 {
    @objid ("de51119d-a8c0-4e41-8047-21013902e374")
    private final List<String[]> moves;

    @objid ("47b4caaf-5786-46ca-bab0-69bb6cb25489")
    private static final String movesFile = "/migration/project_space_migration_1.properties";

    @objid ("2cd3ed2f-d889-477d-8df8-c37cbd22b16c")
    private Collection<Path> movedTargets;

    @objid ("79ab8b29-cb37-425b-83f1-f9b79193e02a")
    private final ProjectDescriptor srcDescriptor;

    @objid ("e7454db8-acb1-4c93-95ff-594d30e12489")
    public  GProjectSpaceFormatMigrator1(ProjectDescriptor srcDescriptor) throws IOException {
        this.srcDescriptor = srcDescriptor;
        this.moves = loadMoves();
        
    }

    @objid ("b0d61497-30b7-481a-9229-0d1fbecc985b")
    public ProjectDescriptor run(SubProgress monitor) throws IOException {
        ProjectDescriptor newDesc = new ProjectDescriptor(this.srcDescriptor);
        
        ProjectFileStructure pfs = newDesc.getProjectFileStructure();
        
        String sdate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.ROOT).format(new Date());
        Path runtimeDir = pfs.getProjectRuntimePath();
        Path logFile = runtimeDir.resolve("migration-" + sdate + ".log");
        
        Files.createDirectories(runtimeDir);
        
        try (PrintWriter logger = new PrintWriter(Files.newBufferedWriter(logFile))) {
        
            logger.format("Log of '%s' project space migration from format %d to %d.\n\n", pfs.getProjectPath(), newDesc.getFormatVersion(), ProjectDescriptor.serialVersionUID);
        
            try {
                moveDirectories(monitor, logger, pfs.getProjectPath());
        
                removeObsoleteEntries(newDesc, logger);
        
                newDesc.setProjectSpaceVersion(ProjectDescriptor.currentProjectSpaceVersion);
                new ProjectDescriptorWriter().write(newDesc);
            } catch (IOException e) {
                logger.format("ERROR: %s", FileUtils.getLocalizedMessage(e));
                e.printStackTrace(logger);
                throw e;
            } catch (RuntimeException e) {
                logger.write("ERROR: ");
                e.printStackTrace(logger);
                throw e;
            }
        }
        return newDesc;
    }

    @objid ("f2ba14bb-6e42-4c74-9381-f6053043b35f")
    private List<String[]> loadMoves() throws IOException {
        Pattern linePattern = Pattern.compile("\\s*([^\\s]+)\\s*=\\s*([^\\s]+)\\s*");
        Pattern commentLinePattern = Pattern.compile("\\s*#.*");
        
        ArrayList<String[]> lmoves = new ArrayList<>();
        
        try (BufferedReader rr = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(GProjectSpaceFormatMigrator1.movesFile), StandardCharsets.UTF_8))) {
            String l;
            int lnum = 0;
            while ((l = rr.readLine()) != null) {
                lnum++;
                l = l.trim();
                if (!l.isEmpty() && !commentLinePattern.matcher(l).matches()) {
                    Matcher m = linePattern.matcher(l);
                    if (!m.matches()) {
                        throw new FileSystemException(getClass().getResource(GProjectSpaceFormatMigrator1.movesFile).toString(), null, String.format("Line %d does not match '%s' : '%s'", lnum, linePattern, l));
                    }
                    String src = m.group(1);
                    String dest = m.group(2);
                    lmoves.add(new String[] { src, dest });
                }
        
            }
        }
        return lmoves;
    }

    @objid ("3247c3cd-8bab-4d4a-a346-3cab0e65b9c0")
    private void moveDirectories(SubProgress monitor, PrintWriter logger, Path projectPath) throws IOException {
        this.movedTargets = new HashSet<>();
        monitor.setWorkRemaining(this.moves.size());
        for (String[] move : this.moves) {
            String src = move[0];
            String dest = move[1];
        
            movePath(monitor, logger, projectPath, src, dest);
        
        }
        
    }

    @objid ("22ee3a62-e286-4bf5-b20b-234052af0538")
    private void movePath(SubProgress monitor, PrintWriter logger, Path projectPath, String src, String dest) throws IOException {
        Path srcPath = projectPath.resolve(src);
        Path destPath = projectPath.resolve(dest);
        
        if (!Files.exists(srcPath)) {
            // Next move ...
            monitor.worked(1);
            return;
        }
        
        monitor.subTask(CoreProject.I18N.getMessage("GProjectFormatMigrator4.moving", src, dest));
        
        Files.walkFileTree(srcPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = destPath.resolve(srcPath.relativize(dir));
                if (GProjectSpaceFormatMigrator1.this.movedTargets.contains(dir)) {
                    logger.format("info: skip moving created '%s' to '%s' to avoid infinite loop.\n", dir, targetPath);
                    return FileVisitResult.SKIP_SUBTREE;
                }
        
                Files.createDirectories(targetPath);
                GProjectSpaceFormatMigrator1.this.movedTargets.add(targetPath);
        
                return FileVisitResult.CONTINUE;
            }
        
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetPath = destPath.resolve(srcPath.relativize(file));
        
                try {
                    Files.move(file, targetPath);
                    logger.format("info: moved '%s' to '%s'.\n", file, targetPath);
                } catch (@SuppressWarnings ("unused") FileAlreadyExistsException e) {
                    Path fileName = targetPath.getFileName();
                    String[] fn = fileName.toString().split("\\.", 2);
                    Path target2 = Files.createTempFile(targetPath.getParent(), fn[0], fn.length > 1 ? fn[1] : "");
        
                    Files.move(file, target2, StandardCopyOption.REPLACE_EXISTING);
                    Log.warning("warn: '%s' file moved to '%s' to avoid overwriting '%s'", file, target2, targetPath);
                    logger.format("warn: '%s' file moved to '%s' to avoid overwriting '%s'.\n", file, target2, targetPath);
        
                }
        
                return FileVisitResult.CONTINUE;
            }
        
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
        
                try {
                    Files.deleteIfExists(dir);
                } catch (DirectoryNotEmptyException e) {
                    logger.format("Note: Cannot delete not empty '%s' directory.\n", dir);
                } catch (IOException e) {
                    logger.format("warn: Cannot delete '%s' directory: '%s'.\n", dir, FileUtils.getLocalizedMessage(e));
                    e.printStackTrace(logger);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        
        monitor.worked(1);
        
    }

    @objid ("5c08714f-0ffe-4c1f-b602-68f0146d5187")
    private void removeObsoleteEntries(ProjectDescriptor newDesc, PrintWriter logger) {
        for (Iterator<Entry> it = newDesc.getProperties().entries().iterator(); it.hasNext();) {
            Entry entry = it.next();
        
            if (entry.getName().startsWith("com.modeliosoft.modelio.viewpoint")) {
                it.remove();
                logger.format("info: removed obsolete '%s' = '%s' (%s) property.\n", entry.getName(), entry.getValue(), entry.getScope());
            }
        }
        
    }

}
