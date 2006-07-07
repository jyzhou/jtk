/* Generated By:JavaCC: Do not edit this line. UnitsParser.java */
package com.lgc.idh.util;

import java.io.StringReader;

/**
 * Parser (generated from UnitsParser.jj via JavaCC) to create Units 
 * from a string that conforms to the following (extended BNF) grammar:
 * <pre>
 * unit -&gt; expr EOF
 * expr -&gt; term ( ("+" | "-") (DOUBLE|INTEGER) )?
 * term -&gt; factor ( ("*")? factor | "/" factor )*
 * factor -&gt; primary ("^" INTEGER)?
 * primary -&gt; NAME | (DOUBLE|INTEGER) | "(" expr ")"
 * </pre>
 *
 * @author Dave Hale
 * @version 1998.07.06
 */
final class UnitsParser implements UnitsParserConstants {

  /** 
   * Parse a String containing a unit definition.
   * @param the unit definition, as in "coulomb/volt".
   * @exception ParseException if the definition is not valid.
   */
  static synchronized Units parse(String definition) throws ParseException {
    ReInit(new StringReader(definition));
    return units();
  }

  static final public Units units() throws ParseException {
  Units e;
    e = expr();
    jj_consume_token(0);
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static final public Units expr() throws ParseException {
  Units t;
  Token n;
    t = term();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
    case MINUS:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOUBLE:
          n = jj_consume_token(DOUBLE);
          break;
        case INTEGER:
          n = jj_consume_token(INTEGER);
          break;
        default:
          jj_la1[0] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      t.shift(Double.valueOf(n.image).doubleValue());
        break;
      case MINUS:
        jj_consume_token(MINUS);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOUBLE:
          n = jj_consume_token(DOUBLE);
          break;
        case INTEGER:
          n = jj_consume_token(INTEGER);
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      t.shift(-Double.valueOf(n.image).doubleValue());
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public Units term() throws ParseException {
  Units f,fb;
    f = factor();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case DIV:
      case LP:
      case NAME:
      case INTEGER:
      case DOUBLE:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MUL:
      case LP:
      case NAME:
      case INTEGER:
      case DOUBLE:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case MUL:
          jj_consume_token(MUL);
          break;
        default:
          jj_la1[5] = jj_gen;
          ;
        }
        fb = factor();
      f.mul(fb);
        break;
      case DIV:
        jj_consume_token(DIV);
        fb = factor();
      f.div(fb);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return f;}
    throw new Error("Missing return statement in function");
  }

  static final public Units factor() throws ParseException {
  Units p;
  Token n;
    p = primary();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case POW:
      jj_consume_token(POW);
      n = jj_consume_token(INTEGER);
      p.pow(Integer.valueOf(n.image).intValue());
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  static final public Units primary() throws ParseException {
  Units e,p;
  Token n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAME:
      n = jj_consume_token(NAME);
    p = Units.unitsFromName(n.image);
    if (p==null) {if (true) throw new ParseException("Units \""+n.image+"\" are undefined.");}
    {if (true) return p;}
      break;
    case INTEGER:
    case DOUBLE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOUBLE:
        n = jj_consume_token(DOUBLE);
        break;
      case INTEGER:
        n = jj_consume_token(INTEGER);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    double d = (new Double(n.image)).doubleValue();
    {if (true) return (new Units()).scale(d);}
      break;
    case LP:
      jj_consume_token(LP);
      e = expr();
      jj_consume_token(RP);
    {if (true) return e;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  static public UnitsParserTokenManager token_source;
  static ASCII_CharStream jj_input_stream;
  static public Token token, jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static final private int[] jj_la1_0 = {0x1800,0x1800,0x18,0x18,0x1d60,0x20,0x1d60,0x80,0x1800,0x1d00,};

  public UnitsParser(java.io.InputStream stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new ASCII_CharStream(stream, 1, 1);
    token_source = new UnitsParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  public UnitsParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new ASCII_CharStream(stream, 1, 1);
    token_source = new UnitsParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  public UnitsParser(UnitsParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  You must");
      System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  public void ReInit(UnitsParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.Vector jj_expentries = new java.util.Vector();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  static final public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[17];
    for (int i = 0; i < 17; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 17; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  static final public void enable_tracing() {
  }

  static final public void disable_tracing() {
  }

}
