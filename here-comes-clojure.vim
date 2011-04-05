" Nasty vim helpers for running the presentation in vimclojure repl


" Move to bottom of repl and start typing
map <leader>` <esc>G$a

" Reload code
map <leader>1 <esc>G$a(use 'here-comes-clojure.core :reload)<cr><esc>
" Begin/Reset presentation
map <leader>2 <esc>G$a(begin!)<cr><esc>
" Advance slide
map <leader>3 <esc>G$a(advance!)<cr><esc>
" Open URL under cursor
map <leader>0 <esc>viwyG$a(browse "<esc>pa")<cr><esc>
" eval form. Cursor must be on open or close paren.
map <leader>9 <esc>"ay%:let @a=substitute(@a,"\|","","g")<cr>G$"ap$a<cr><esc>
