function! <SID>AddPackageLine()
  let pathparts = split(expand("%:p:h"), "/")
  let srcidx = index(pathparts, "com")
  if srcidx > -1
    let @" = "package " . join(pathparts[srcidx : ], ".") . ";\n"
    normal P
  end
endf

nmap <leader>p :call <SID>AddPackageLine()<Cr>

set wildignore+=*/target/*
let java_allow_cpp_keywords=1
