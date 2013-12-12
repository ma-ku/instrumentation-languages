package de.cau.cs.se.instrumentation.rl.generator.java;

import com.google.common.base.Objects;
import de.cau.cs.se.instrumentation.rl.generator.AbstractPartialRecordTypeGenerator;
import de.cau.cs.se.instrumentation.rl.recordLang.Classifier;
import de.cau.cs.se.instrumentation.rl.recordLang.Model;
import de.cau.cs.se.instrumentation.rl.recordLang.PartialRecordType;
import de.cau.cs.se.instrumentation.rl.recordLang.PartialType;
import de.cau.cs.se.instrumentation.rl.recordLang.Property;
import de.cau.cs.se.instrumentation.rl.recordLang.Type;
import java.io.File;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class PartialRecordTypeGenerator extends AbstractPartialRecordTypeGenerator {
  public CharSequence createContent(final PartialRecordType type, final String author, final String version) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/***************************************************************************");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Copyright 2013 Kieker Project (http://kieker-monitoring.net)");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Licensed under the Apache License, Version 2.0 (the \"License\");");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* you may not use this file except in compliance with the License.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* You may obtain a copy of the License at");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*     http://www.apache.org/licenses/LICENSE-2.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Unless required by applicable law or agreed to in writing, software");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* distributed under the License is distributed on an \"AS IS\" BASIS,");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* See the License for the specific language governing permissions and");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* limitations under the License.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("***************************************************************************/");
    _builder.newLine();
    _builder.newLine();
    _builder.append("package ");
    EObject _eContainer = type.eContainer();
    String _name = ((Model) _eContainer).getName();
    _builder.append(_name, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.nio.BufferOverflowException;");
    _builder.newLine();
    _builder.append("import java.nio.BufferUnderflowException;");
    _builder.newLine();
    _builder.append("import java.io.UnsupportedEncodingException;");
    _builder.newLine();
    _builder.append("import java.nio.ByteBuffer;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import kieker.common.record.AbstractMonitoringRecord;");
    _builder.newLine();
    _builder.append("import kieker.common.record.IMonitoringRecord;");
    _builder.newLine();
    _builder.append("import kieker.common.util.registry.IRegistry;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author ");
    _builder.append(author, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @since ");
    _builder.append(version, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public interface ");
    String _name_1 = type.getName();
    _builder.append(_name_1, "");
    CharSequence _xifexpression = null;
    boolean _and = false;
    EList<PartialType> _parents = type.getParents();
    boolean _notEquals = (!Objects.equal(_parents, null));
    if (!_notEquals) {
      _and = false;
    } else {
      EList<PartialType> _parents_1 = type.getParents();
      int _size = _parents_1.size();
      boolean _greaterThan = (_size > 0);
      _and = (_notEquals && _greaterThan);
    }
    if (_and) {
      EList<PartialType> _parents_2 = type.getParents();
      CharSequence _createExtends = this.createExtends(_parents_2);
      _xifexpression = _createExtends;
    }
    _builder.append(_xifexpression, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    EList<Property> _properties = type.getProperties();
    final Function1<Property,CharSequence> _function = new Function1<Property,CharSequence>() {
      public CharSequence apply(final Property property) {
        CharSequence _createPropertyGetter = PartialRecordTypeGenerator.this.createPropertyGetter(property);
        return _createPropertyGetter;
      }
    };
    List<CharSequence> _map = ListExtensions.<Property, CharSequence>map(_properties, _function);
    String _join = IterableExtensions.join(_map);
    _builder.append(_join, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createExtends(final EList<PartialType> parents) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("extends ");
    final Function1<PartialType,String> _function = new Function1<PartialType,String>() {
      public String apply(final PartialType t) {
        String _name = t.getName();
        return _name;
      }
    };
    List<String> _map = ListExtensions.<PartialType, String>map(parents, _function);
    String _join = IterableExtensions.join(_map, ", ");
    _builder.append(_join, " ");
    return _builder;
  }
  
  /**
   * Creates a getter for a given property.
   * 
   * @param property
   * 		a property of the record type
   * 
   * @returns the resulting getter as a CharSequence
   */
  public CharSequence createPropertyGetter(final Property property) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    Classifier _type = property.getType();
    CharSequence _createTypeName = this.createTypeName(_type);
    _builder.append(_createTypeName, "");
    _builder.append(" get");
    String _name = property.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("() ;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Determine the right Java string for a given system type.
   * 
   * @param classifier
   * 		a classifier representing a type
   * 
   * @returns a java type name
   */
  public CharSequence createTypeName(final Classifier classifier) {
    String _switchResult = null;
    EClassifier _class_ = classifier.getClass_();
    String _name = _class_.getName();
    final String _switchValue = _name;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_switchValue,"string")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"key")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      EClassifier _class__1 = classifier.getClass_();
      String _name_1 = _class__1.getName();
      _switchResult = _name_1;
    }
    return _switchResult;
  }
  
  /**
   * Compute the directory name for a record type.
   */
  public CharSequence directoryName(final Type type) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("java");
    _builder.append(File.separator, "");
    EObject _eContainer = type.eContainer();
    String _name = ((Model) _eContainer).getName();
    String _replace = _name.replace(".", File.separator);
    _builder.append(_replace, "");
    return _builder;
  }
  
  public String fileName(final Type type) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _directoryName = this.directoryName(type);
    _builder.append(_directoryName, "");
    _builder.append(File.separator, "");
    String _name = type.getName();
    _builder.append(_name, "");
    _builder.append(".java");
    return _builder.toString();
  }
}