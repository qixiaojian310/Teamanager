import themes from './theme.json'


export function addColor(dark,color) {
    const isDark = dark
    // const color = localStorage.color
    const { white, black, obj = {} } = themes
    console.log(obj);
    console.log(white);
    console.log(black);
    // 1.localStorage.theme 不存在默认黑色 2.存在并且刷新页面不改变主题 3.存在并且点切换才改变主题
    if (isDark) {
        setColor(color, black)
    } else {
        setColor(color, white)
    }
    console.log(obj);


}

function setColor(color, theme) {
    const { green, red, yellow, blue, obj = {} } = theme;
    if (color == 'green') {
        Object.assign(obj, green)
    } else if (color == 'red') {
        Object.assign(obj, red)
    } else if (color == 'blue') {
        Object.assign(obj, blue)
    } else if (color == 'yellow') {
        Object.assign(obj, yellow)
    }
    // html 添加主题样式
    for (const key in obj) {
        document.documentElement.style.setProperty(key, obj[key]);
    }
}