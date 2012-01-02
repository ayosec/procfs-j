package com.ayosec.procfs;

public class ErrnoException extends Exception {
  private int errno;
  private String argument;

  public ErrnoException(int errno) {
    this.errno = errno;
    this.argument = "";
  }

  public ErrnoException(int errno, String argument) {
    this.errno = errno;
    this.argument = argument;
  }

  public int getError() {
    return this.errno;
  }

  public String getArgument() {
    return this.argument;
  }

  public String getMessage() {
    return LibC.Handle.module.strerror(this.errno);
  }

  public String getErrorName() {
    return getNameFromErrorValue(this.errno);
  }

  public class Errors {

    // Based on /usr/include/**/*errno* files

    final static public int EPERM           = 1;
    final static public int ENOENT          = 2;
    final static public int ESRCH           = 3;
    final static public int EINTR           = 4;
    final static public int EIO             = 5;
    final static public int ENXIO           = 6;
    final static public int E2BIG           = 7;
    final static public int ENOEXEC         = 8;
    final static public int EBADF           = 9;
    final static public int ECHILD          = 10;
    final static public int EAGAIN          = 11;
    final static public int ENOMEM          = 12;
    final static public int EACCES          = 13;
    final static public int EFAULT          = 14;
    final static public int ENOTBLK         = 15;
    final static public int EBUSY           = 16;
    final static public int EEXIST          = 17;
    final static public int EXDEV           = 18;
    final static public int ENODEV          = 19;
    final static public int ENOTDIR         = 20;
    final static public int EISDIR          = 21;
    final static public int EINVAL          = 22;
    final static public int ENFILE          = 23;
    final static public int EMFILE          = 24;
    final static public int ENOTTY          = 25;
    final static public int ETXTBSY         = 26;
    final static public int EFBIG           = 27;
    final static public int ENOSPC          = 28;
    final static public int ESPIPE          = 29;
    final static public int EROFS           = 30;
    final static public int EMLINK          = 31;
    final static public int EPIPE           = 32;
    final static public int EDOM            = 33;
    final static public int ERANGE          = 34;
    final static public int EDEADLK         = 35;
    final static public int ENAMETOOLONG    = 36;
    final static public int ENOLCK          = 37;
    final static public int ENOSYS          = 38;
    final static public int ENOTEMPTY       = 39;
    final static public int ELOOP           = 40;
    final static public int ENOMSG          = 42;
    final static public int EIDRM           = 43;
    final static public int ECHRNG          = 44;
    final static public int EL2NSYNC        = 45;
    final static public int EL3HLT          = 46;
    final static public int EL3RST          = 47;
    final static public int ELNRNG          = 48;
    final static public int EUNATCH         = 49;
    final static public int ENOCSI          = 50;
    final static public int EL2HLT          = 51;
    final static public int EBADE           = 52;
    final static public int EBADR           = 53;
    final static public int EXFULL          = 54;
    final static public int ENOANO          = 55;
    final static public int EBADRQC         = 56;
    final static public int EBADSLT         = 57;
    final static public int EBFONT          = 59;
    final static public int ENOSTR          = 60;
    final static public int ENODATA         = 61;
    final static public int ETIME           = 62;
    final static public int ENOSR           = 63;
    final static public int ENONET          = 64;
    final static public int ENOPKG          = 65;
    final static public int EREMOTE         = 66;
    final static public int ENOLINK         = 67;
    final static public int EADV            = 68;
    final static public int ESRMNT          = 69;
    final static public int ECOMM           = 70;
    final static public int EPROTO          = 71;
    final static public int EMULTIHOP       = 72;
    final static public int EDOTDOT         = 73;
    final static public int EBADMSG         = 74;
    final static public int EOVERFLOW       = 75;
    final static public int ENOTUNIQ        = 76;
    final static public int EBADFD          = 77;
    final static public int EREMCHG         = 78;
    final static public int ELIBACC         = 79;
    final static public int ELIBBAD         = 80;
    final static public int ELIBSCN         = 81;
    final static public int ELIBMAX         = 82;
    final static public int ELIBEXEC        = 83;
    final static public int EILSEQ          = 84;
    final static public int ERESTART        = 85;
    final static public int ESTRPIPE        = 86;
    final static public int EUSERS          = 87;
    final static public int ENOTSOCK        = 88;
    final static public int EDESTADDRREQ    = 89;
    final static public int EMSGSIZE        = 90;
    final static public int EPROTOTYPE      = 91;
    final static public int ENOPROTOOPT     = 92;
    final static public int EPROTONOSUPPORT = 93;
    final static public int ESOCKTNOSUPPORT = 94;
    final static public int EOPNOTSUPP      = 95;
    final static public int EPFNOSUPPORT    = 96;
    final static public int EAFNOSUPPORT    = 97;
    final static public int EADDRINUSE      = 98;
    final static public int EADDRNOTAVAIL   = 99;
    final static public int ENETDOWN        = 100;
    final static public int ENETUNREACH     = 101;
    final static public int ENETRESET       = 102;
    final static public int ECONNABORTED    = 103;
    final static public int ECONNRESET      = 104;
    final static public int ENOBUFS         = 105;
    final static public int EISCONN         = 106;
    final static public int ENOTCONN        = 107;
    final static public int ESHUTDOWN       = 108;
    final static public int ETOOMANYREFS    = 109;
    final static public int ETIMEDOUT       = 110;
    final static public int ECONNREFUSED    = 111;
    final static public int EHOSTDOWN       = 112;
    final static public int EHOSTUNREACH    = 113;
    final static public int EALREADY        = 114;
    final static public int EINPROGRESS     = 115;
    final static public int ESTALE          = 116;
    final static public int EUCLEAN         = 117;
    final static public int ENOTNAM         = 118;
    final static public int ENAVAIL         = 119;
    final static public int EISNAM          = 120;
    final static public int EREMOTEIO       = 121;
    final static public int EDQUOT          = 122;
    final static public int ENOMEDIUM       = 123;
    final static public int EMEDIUMTYPE     = 124;
    final static public int ECANCELED       = 125;
    final static public int ENOKEY          = 126;
    final static public int EKEYEXPIRED     = 127;
    final static public int EKEYREVOKED     = 128;
    final static public int EKEYREJECTED    = 129;
    final static public int EOWNERDEAD      = 130;
    final static public int ENOTRECOVERABLE = 131;
    final static public int ERFKILL         = 132;
    final static public int EHWPOISON       = 133;

  }

  public static String getNameFromErrorValue(int value) {
    switch(value) {
      case Errors.EPERM:           return "EPERM";
      case Errors.ENOENT:          return "ENOENT";
      case Errors.ESRCH:           return "ESRCH";
      case Errors.EINTR:           return "EINTR";
      case Errors.EIO:             return "EIO";
      case Errors.ENXIO:           return "ENXIO";
      case Errors.E2BIG:           return "E2BIG";
      case Errors.ENOEXEC:         return "ENOEXEC";
      case Errors.EBADF:           return "EBADF";
      case Errors.ECHILD:          return "ECHILD";
      case Errors.EAGAIN:          return "EAGAIN";
      case Errors.ENOMEM:          return "ENOMEM";
      case Errors.EACCES:          return "EACCES";
      case Errors.EFAULT:          return "EFAULT";
      case Errors.ENOTBLK:         return "ENOTBLK";
      case Errors.EBUSY:           return "EBUSY";
      case Errors.EEXIST:          return "EEXIST";
      case Errors.EXDEV:           return "EXDEV";
      case Errors.ENODEV:          return "ENODEV";
      case Errors.ENOTDIR:         return "ENOTDIR";
      case Errors.EISDIR:          return "EISDIR";
      case Errors.EINVAL:          return "EINVAL";
      case Errors.ENFILE:          return "ENFILE";
      case Errors.EMFILE:          return "EMFILE";
      case Errors.ENOTTY:          return "ENOTTY";
      case Errors.ETXTBSY:         return "ETXTBSY";
      case Errors.EFBIG:           return "EFBIG";
      case Errors.ENOSPC:          return "ENOSPC";
      case Errors.ESPIPE:          return "ESPIPE";
      case Errors.EROFS:           return "EROFS";
      case Errors.EMLINK:          return "EMLINK";
      case Errors.EPIPE:           return "EPIPE";
      case Errors.EDOM:            return "EDOM";
      case Errors.ERANGE:          return "ERANGE";
      case Errors.EDEADLK:         return "EDEADLK";
      case Errors.ENAMETOOLONG:    return "ENAMETOOLONG";
      case Errors.ENOLCK:          return "ENOLCK";
      case Errors.ENOSYS:          return "ENOSYS";
      case Errors.ENOTEMPTY:       return "ENOTEMPTY";
      case Errors.ELOOP:           return "ELOOP";
      case Errors.ENOMSG:          return "ENOMSG";
      case Errors.EIDRM:           return "EIDRM";
      case Errors.ECHRNG:          return "ECHRNG";
      case Errors.EL2NSYNC:        return "EL2NSYNC";
      case Errors.EL3HLT:          return "EL3HLT";
      case Errors.EL3RST:          return "EL3RST";
      case Errors.ELNRNG:          return "ELNRNG";
      case Errors.EUNATCH:         return "EUNATCH";
      case Errors.ENOCSI:          return "ENOCSI";
      case Errors.EL2HLT:          return "EL2HLT";
      case Errors.EBADE:           return "EBADE";
      case Errors.EBADR:           return "EBADR";
      case Errors.EXFULL:          return "EXFULL";
      case Errors.ENOANO:          return "ENOANO";
      case Errors.EBADRQC:         return "EBADRQC";
      case Errors.EBADSLT:         return "EBADSLT";
      case Errors.EBFONT:          return "EBFONT";
      case Errors.ENOSTR:          return "ENOSTR";
      case Errors.ENODATA:         return "ENODATA";
      case Errors.ETIME:           return "ETIME";
      case Errors.ENOSR:           return "ENOSR";
      case Errors.ENONET:          return "ENONET";
      case Errors.ENOPKG:          return "ENOPKG";
      case Errors.EREMOTE:         return "EREMOTE";
      case Errors.ENOLINK:         return "ENOLINK";
      case Errors.EADV:            return "EADV";
      case Errors.ESRMNT:          return "ESRMNT";
      case Errors.ECOMM:           return "ECOMM";
      case Errors.EPROTO:          return "EPROTO";
      case Errors.EMULTIHOP:       return "EMULTIHOP";
      case Errors.EDOTDOT:         return "EDOTDOT";
      case Errors.EBADMSG:         return "EBADMSG";
      case Errors.EOVERFLOW:       return "EOVERFLOW";
      case Errors.ENOTUNIQ:        return "ENOTUNIQ";
      case Errors.EBADFD:          return "EBADFD";
      case Errors.EREMCHG:         return "EREMCHG";
      case Errors.ELIBACC:         return "ELIBACC";
      case Errors.ELIBBAD:         return "ELIBBAD";
      case Errors.ELIBSCN:         return "ELIBSCN";
      case Errors.ELIBMAX:         return "ELIBMAX";
      case Errors.ELIBEXEC:        return "ELIBEXEC";
      case Errors.EILSEQ:          return "EILSEQ";
      case Errors.ERESTART:        return "ERESTART";
      case Errors.ESTRPIPE:        return "ESTRPIPE";
      case Errors.EUSERS:          return "EUSERS";
      case Errors.ENOTSOCK:        return "ENOTSOCK";
      case Errors.EDESTADDRREQ:    return "EDESTADDRREQ";
      case Errors.EMSGSIZE:        return "EMSGSIZE";
      case Errors.EPROTOTYPE:      return "EPROTOTYPE";
      case Errors.ENOPROTOOPT:     return "ENOPROTOOPT";
      case Errors.EPROTONOSUPPORT: return "EPROTONOSUPPORT";
      case Errors.ESOCKTNOSUPPORT: return "ESOCKTNOSUPPORT";
      case Errors.EOPNOTSUPP:      return "EOPNOTSUPP";
      case Errors.EPFNOSUPPORT:    return "EPFNOSUPPORT";
      case Errors.EAFNOSUPPORT:    return "EAFNOSUPPORT";
      case Errors.EADDRINUSE:      return "EADDRINUSE";
      case Errors.EADDRNOTAVAIL:   return "EADDRNOTAVAIL";
      case Errors.ENETDOWN:        return "ENETDOWN";
      case Errors.ENETUNREACH:     return "ENETUNREACH";
      case Errors.ENETRESET:       return "ENETRESET";
      case Errors.ECONNABORTED:    return "ECONNABORTED";
      case Errors.ECONNRESET:      return "ECONNRESET";
      case Errors.ENOBUFS:         return "ENOBUFS";
      case Errors.EISCONN:         return "EISCONN";
      case Errors.ENOTCONN:        return "ENOTCONN";
      case Errors.ESHUTDOWN:       return "ESHUTDOWN";
      case Errors.ETOOMANYREFS:    return "ETOOMANYREFS";
      case Errors.ETIMEDOUT:       return "ETIMEDOUT";
      case Errors.ECONNREFUSED:    return "ECONNREFUSED";
      case Errors.EHOSTDOWN:       return "EHOSTDOWN";
      case Errors.EHOSTUNREACH:    return "EHOSTUNREACH";
      case Errors.EALREADY:        return "EALREADY";
      case Errors.EINPROGRESS:     return "EINPROGRESS";
      case Errors.ESTALE:          return "ESTALE";
      case Errors.EUCLEAN:         return "EUCLEAN";
      case Errors.ENOTNAM:         return "ENOTNAM";
      case Errors.ENAVAIL:         return "ENAVAIL";
      case Errors.EISNAM:          return "EISNAM";
      case Errors.EREMOTEIO:       return "EREMOTEIO";
      case Errors.EDQUOT:          return "EDQUOT";
      case Errors.ENOMEDIUM:       return "ENOMEDIUM";
      case Errors.EMEDIUMTYPE:     return "EMEDIUMTYPE";
      case Errors.ECANCELED:       return "ECANCELED";
      case Errors.ENOKEY:          return "ENOKEY";
      case Errors.EKEYEXPIRED:     return "EKEYEXPIRED";
      case Errors.EKEYREVOKED:     return "EKEYREVOKED";
      case Errors.EKEYREJECTED:    return "EKEYREJECTED";
      case Errors.EOWNERDEAD:      return "EOWNERDEAD";
      case Errors.ENOTRECOVERABLE: return "ENOTRECOVERABLE";
      case Errors.ERFKILL:         return "ERFKILL";
      case Errors.EHWPOISON:       return "EHWPOISON";
      default:                     return "<" + value + ">";
    }
  }
}

