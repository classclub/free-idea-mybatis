package com.seventh7.mybatis.service;

import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.formatting.FormatTextRanges;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.impl.source.codeStyle.CodeFormatterFacade;

import org.jetbrains.annotations.NotNull;

/**
 * @author yanglin
 */
public class EditorService {

  private Project project;

  public EditorService(Project project) {
    this.project = project;
  }

  public static final EditorService getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, EditorService.class);
  }

  public void format(@NotNull PsiElement element) {
    CodeFormatterFacade formatter = new CodeFormatterFacade(new CodeStyleSettings());
    formatter.processText(element.getContainingFile(), new FormatTextRanges(element.getTextRange(), true), true);
  }

  public void scrollTo(@NotNull PsiElement element, int offset) {
    NavigationUtil.activateFileWithPsiElement(element, true);
    Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
    editor.getCaretModel().moveToOffset(offset);
    editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
  }

}
