/**
 * Copyright (c) 2005-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.python.pydev.analysis.ctrl_1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.python.pydev.core.IPythonNature;
import org.python.pydev.core.docutils.PySelection;
import org.python.pydev.editor.PyEdit;
import org.python.pydev.editor.correctionassist.IgnoreCompletionProposal;
import org.python.pydev.editor.correctionassist.heuristics.IAssistProps;
import org.python.pydev.shared_core.code_completion.ICompletionProposalHandle;
import org.python.pydev.shared_core.image.IImageCache;
import org.python.pydev.shared_core.image.IImageHandle;
import org.python.pydev.shared_ui.SharedUiPlugin;
import org.python.pydev.shared_ui.UIConstants;
import org.python.pydev.shared_ui.proposals.PyCompletionProposal;

import com.python.pydev.analysis.builder.AnalysisRunner;

public class DontAnalyzeFileMarkerParticipant implements IAssistProps {

    private IImageHandle annotationImage;

    public DontAnalyzeFileMarkerParticipant() {
        IImageCache analysisImageCache = SharedUiPlugin.getImageCache();
        annotationImage = analysisImageCache.get(UIConstants.ASSIST_ANNOTATION);
    }

    @Override
    public List<ICompletionProposalHandle> getProps(PySelection ps, IImageCache imageCache, File f,
            IPythonNature nature,
            PyEdit edit, int offset) throws BadLocationException {
        List<ICompletionProposalHandle> props = new ArrayList<ICompletionProposalHandle>();
        if (ps.getCursorLine() == 0) {
            String replacementString = '#' + AnalysisRunner.PYDEV_CODE_ANALYSIS_IGNORE + ps.getEndLineDelim();

            IgnoreCompletionProposal proposal = new IgnoreCompletionProposal(
                    replacementString,
                    0,
                    0,
                    offset + replacementString.length(),
                    annotationImage,
                    AnalysisRunner.PYDEV_CODE_ANALYSIS_IGNORE,
                    null,
                    null,
                    PyCompletionProposal.PRIORITY_DEFAULT,
                    edit);
            props.add(proposal);

        }
        return props;
    }

    @Override
    public boolean isValid(PySelection ps, String sel, PyEdit edit, int offset) {
        return ps.getCursorLine() == 0
                && ps.getCursorLineContents().indexOf(AnalysisRunner.PYDEV_CODE_ANALYSIS_IGNORE) == -1;
    }

}
